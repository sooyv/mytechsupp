package techsuppDev.techsupp.repository;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.DTO.Paylog;
import techsuppDev.techsupp.controller.form.PayHistoryForm;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

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

//    상품 선택 했을때 그 상품에 대한 결제 정보가 있는지 확인하기 위해서 join 한 것
    public PayHistoryForm checkPaymentPaylogJoin(String userEmail, Long productId) {
        String sql = "" +
                "select * from (" +
                "select * from paylog " +
                "where user_email = '" + userEmail + "') as userpay " +
                "inner join payment " +
                "using (payment_id) " +
                "where product_id = " +
                productId + ";";
//        Query nativeQuery = em.createNativeQuery(sql);

            System.out.println("checkPayment-Paylog join : 검색 결과 조회중");
            try {
                Query nativeQuery = em.createNativeQuery(sql, PayHistoryForm.class);
                PayHistoryForm result = (PayHistoryForm) nativeQuery.getSingleResult();
                System.out.println();
                System.out.println("result try: " + result.toString());
                return result;
            } catch (Exception e) {
                System.out.println("checkPayment-Paylog join : 검색 결과가 존재하지 않음");
                PayHistoryForm result = null;
                System.out.println("result Exception : " + result);
                return result;
            }
    }
}
