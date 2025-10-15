import com.fiap.fintech.dao.InvestmentDAO;
import com.fiap.fintech.domain.Investment;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestInvestmentDAO {
    public static void main(String[] args) {
        InvestmentDAO dao = new InvestmentDAO();

        // IDs manuais — troque se já existir para evitar ORA-00001
        dao.insert(new Investment(306L, LocalDate.now(),
                new BigDecimal("1000.00"), new BigDecimal("0.12"), 12, "CDB 100% CDI"));
        dao.insert(new Investment(307L, LocalDate.now().minusDays(1),
                new BigDecimal("500.00"), new BigDecimal("0.10"), 6, "Tesouro"));
        dao.insert(new Investment(308L, LocalDate.now().minusDays(2),
                new BigDecimal("2500.00"), new BigDecimal("0.15"), 24, "LCI"));
        dao.insert(new Investment(309L, LocalDate.now().minusDays(3),
                new BigDecimal("800.00"), new BigDecimal("0.09"), 9, "LCA"));
        dao.insert(new Investment(310L, LocalDate.now().minusDays(4),
                new BigDecimal("1500.00"), new BigDecimal("0.11"), 18, "CDB"));


        List<Investment> all = dao.getAll();
        System.out.println("Total investments: " + all.size());
        all.forEach(System.out::println);
    }
}
