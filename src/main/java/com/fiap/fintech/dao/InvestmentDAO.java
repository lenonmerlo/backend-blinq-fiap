package com.fiap.fintech.dao;

import com.fiap.fintech.domain.Investment;
import com.fiap.fintech.factory.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvestmentDAO {

    private static final String INSERT_SQL =
            "INSERT INTO INVESTIMENTO (ID_INVESTIMENTO, DATA_APLICACAO, VALOR_APLICADO, RENDIMENTO_ESTIMADO, PRAZO) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT ID_INVESTIMENTO, DATA_APLICACAO, VALOR_APLICADO, RENDIMENTO_ESTIMADO, PRAZO " +
                    "FROM INVESTIMENTO ORDER BY ID_INVESTIMENTO";

    public void insert(Investment inv) {
        if (inv == null) throw new IllegalArgumentException("Investment não pode ser nulo");
        if (inv.getId() == null) throw new IllegalArgumentException("ID é obrigatório");
        if (inv.getApplicationDate() == null) throw new IllegalArgumentException("DATA_APLICACAO é obrigatória");
        if (inv.getAmount() == null) throw new IllegalArgumentException("VALOR_APLICADO é obrigatório");
        if (inv.getAnnualRate() == null) throw new IllegalArgumentException("RENDIMENTO_ESTIMADO é obrigatório");
        if (inv.getTerm() == null) throw new IllegalArgumentException("PRAZO é obrigatório");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setLong(1, inv.getId());
            ps.setDate(2, Date.valueOf(inv.getApplicationDate()));
            ps.setBigDecimal(3, inv.getAmount());
            ps.setBigDecimal(4, inv.getAnnualRate());
            ps.setInt(5, inv.getTerm());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir investment no Oracle", ex);
        }
    }

    public List<Investment> getAll() {
        List<Investment> list = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("ID_INVESTIMENTO");
                Date d = rs.getDate("DATA_APLICACAO");
                LocalDate applicationDate = (d != null) ? d.toLocalDate() : null;
                BigDecimal amountApplied = rs.getBigDecimal("VALOR_APLICADO");
                BigDecimal estimatedYield = rs.getBigDecimal("RENDIMENTO_ESTIMADO");
                Integer term = rs.getInt("PRAZO");

                // productName não vem do DB -> deixamos null
                Investment inv = new Investment(id, applicationDate, amountApplied, estimatedYield, term, null);
                list.add(inv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar investments no Oracle", ex);
        }

        return list;
    }
}
