package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class PaymentRepository {
    private final EntityManager em;

//    특정 상품의 결제 로그 저장

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

        Query nativeQuery = em.createNativeQuery(sql, PaymentForm.class);


        System.out.println(nativeQuery);



    }
}
