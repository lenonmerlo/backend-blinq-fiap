package com.fiap.fintech.dao;

import com.fiap.fintech.domain.Expense;
import com.fiap.fintech.factory.ConnectionFactory;

import javax.swing.plaf.PanelUI;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    private static final String INSERT_SQL =
          "INSERT INTO GASTO (ID_GASTO, DATA_GASTO, VALOR, DESCRICAO, FORMA_PAGAMENTO) " + "VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
           "SELECT ID_GASTO, DATA_GASTO, VALOR, DESCRICAO, FORMA_PAGAMENTO FROM GASTO ORDER BY ID_GASTO";

    public void insert(Expense e) {
        if (e == null) throw new IllegalArgumentException("Expense não pode ser nulo");
        if (e.getId() == null) throw new IllegalArgumentException("ID é obrigatório (gerado pela aplicação)");
        if (e.getDate() == null) throw new IllegalArgumentException("DATA_GASTO é obrigatória.");
        if (e.getAmount() == null) throw new IllegalArgumentException("VALOR é obrigatório");
        if (e.getPaymentMethod() == null) throw new IllegalArgumentException("FORMA_PAGAMENTO é obrigatória.");
        if (e.getDescription() == null) throw new IllegalArgumentException("DESCRICAO é obrigatória.");

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setLong(1, e.getId());
            ps.setDate(2, Date.valueOf(e.getDate()));
            ps.setBigDecimal(3, e.getAmount());
            ps.setString(4, e.getDescription());
            ps.setString(5, e.getPaymentMethod());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao inserir expense no Oracle", ex);
        }
    }

    public List<Expense> getAll() {
        List<Expense> list = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Long id = rs.getLong("ID_GASTO");
                Date d = rs.getDate("DATA_GASTO");
                LocalDate date = (d != null) ? d.toLocalDate() : null;
                BigDecimal amount = rs.getBigDecimal("VALOR");
                String description = rs.getString("DESCRICAO");
                String paymentMethod = rs.getString("FORMA_PAGAMENTO");

                Expense e = new Expense(id, date, amount, description, paymentMethod);
                list.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Erro ao consultar expense no Oracle", ex);
        }

        return list;
    }

}
