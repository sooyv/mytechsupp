package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
    private final EntityManager em;

//    특정 상품의 결제 로그 저장
//
    @Transactional // native queryd의 select 이외의 기능을 수행할때 에러 발생 방지를 위한 annotation
    @Modifying // executeUpdate()의 실행을 select가 아님 update로 인식 시키기 위한 annotation
    public void savePayment(PaymentForm payment) {
        String sql = " " +
            "insert payment(" +
            "detail_addr, payment_date, payment_method, payment_price, product_id, street_addr, zip_code) " +
            "values(" +
            payment.getDetailAddr() + ", '" +
            payment.getPaymentDate() + "', '" +
            payment.getPaymentMethod() + "', " +
            payment.getPaymentPrice() + ", " +
            payment.getProductId() + ", '" +
            payment.getStreetAddr() + "', " +
            payment.getZipCode() + ")";

        Query nativeQuery = em.createNativeQuery(sql, Payment.class);
        nativeQuery.executeUpdate();
    }
}
