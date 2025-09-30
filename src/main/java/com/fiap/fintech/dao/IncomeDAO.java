package com.fiap.fintech.dao;

import com.fiap.fintech.domain.Income;
import com.fiap.fintech.factory.ConnectionFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDAO {

    private static final String INSERT_SQL =
            "INSERT INTO RECEBIMENTO (ID_RECEBIMENTO, DATA_RECEBIMENTO, VALOR, DESCRICAO, ORIGEM) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT ID_RECEBIMENTO, DATA_RECEBIMENTO, VALOR, DESCRICAO, ORIGEM FROM RECEBIMENTO ORDER BY ID_RECEBIMENTO";

    public void insert(Income i) {
        if (i == null) throw new IllegalArgumentException("Income não pode ser nulo");
        if (i.getId() == null) throw new IllegalArgumentException("ID é obrigatório (gerado pela aplicação)");
        if (i.getDate() == null) throw new IllegalArgumentException("DATA_RECEBIMENTO é obrigatória");
        if (i.getAmount() == null) throw new IllegalArgumentException("VALOR é obrigatório");
        if (i.getDescription() == null) throw new IllegalArgumentException("DESCRICAO é obrigatória");
        if (i.getSource() == null) throw new IllegalArgumentException("ORIGEM é obrigatória");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setLong(1, i.getId());
            ps.setDate(2, Date.valueOf(i.getDate()));
            ps.setBigDecimal(3, i.getAmount());
            ps.setString(4, i.getDescription());
            ps.setString(5, i.getSource());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir income no Oracle", ex);
        }
    }

    public List<Income> getAll() {
        List<Income> list = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("ID_RECEBIMENTO");
                Date d = rs.getDate("DATA_RECEBIMENTO");
                LocalDate date = (d != null) ? d.toLocalDate() : null;
                BigDecimal amount = rs.getBigDecimal("VALOR");
                String description = rs.getString("DESCRICAO");
                String source = rs.getString("ORIGEM");

                Income i = new Income(id, date, amount, description, source);
                list.add(i);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar incomes no Oracle", ex);
        }

        return list;
    }
}
