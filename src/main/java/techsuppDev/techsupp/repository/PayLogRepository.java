package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.DTO.Paylog;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class PayLogRepository {
    private final EntityManager em;

    @Transactional
    @Modifying
    public void savePaylog(Paylog paylog) {
        String sql = "" +
                "insert paylog(" +
                "user_email, payment_id, paylog_status) " +
                "values('" +
                paylog.getUserEmail() + "', " +
                paylog.getPaymentId() + ", '" +
                paylog.getPaylogStatus() + "');";

        Query navtiveQuery = em.createNativeQuery(sql, Paylog.class);
        navtiveQuery.executeUpdate();
    }
}
