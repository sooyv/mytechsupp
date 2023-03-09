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

//    결제 정보 저장
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
//    로그인한 고객의 결제 정보 있는지 판별

    public Object checkPaylog(String userEmail) {
        String sql = "" +
                "select * from paylog " +
                "where " +
                "user_email = '" +
                userEmail + "';";
//        Query nativeQuery = em.createNativeQuery(sql, Paylog.class);
        try {
            Query nativeQuery = em.createNativeQuery(sql, Paylog.class);
            return nativeQuery;
        } catch (Exception e) {
            System.out.println("checkpaylog : 검색되지 않음");
            Object result = null;
            System.out.println("반환갑:  null ");
            return result;
        }


    }
}
