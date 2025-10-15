import com.fiap.fintech.dao.ExpenseDAO;
import com.fiap.fintech.domain.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestExpenseDAO {
    public static void main(String[] args) {
        ExpenseDAO dao = new ExpenseDAO();

        // --- INSERT de 5 registros (ajuste IDs se já tiver rodado antes) ---
        dao.insert(new Expense(10L, LocalDate.now(), new BigDecimal("89.90"), "Groceries", "DEBIT"));
        dao.insert(new Expense(20L, LocalDate.now().minusDays(1), new BigDecimal("25.00"), "Bus ticket", "CASH"));
        dao.insert(new Expense(30L, LocalDate.now().minusDays(2), new BigDecimal("150.00"), "Gym monthly", "PIX"));
        dao.insert(new Expense(40L, LocalDate.now().minusDays(3), new BigDecimal("39.99"), "Streaming", "CREDIT"));
        dao.insert(new Expense(50L, LocalDate.now().minusDays(4), new BigDecimal("12.50"), "Coffee", "CREDIT"));

        // --- SELECT ALL ---
        List<Expense> all = dao.getAll();
        System.out.println("Total expenses: " + all.size());
        all.forEach(System.out::println);
    }
}
