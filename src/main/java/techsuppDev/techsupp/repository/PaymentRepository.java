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
            "values('" +
            payment.getDetailAddr() + "', '" +
            payment.getPaymentDate() + "', '" +
            payment.getPaymentMethod() + "', " +
            payment.getPaymentPrice() + ", " +
            payment.getProductId() + ", '" +
            payment.getStreetAddr() + "', '" +
            payment.getZipCode() + "')";

        Query nativeQuery = em.createNativeQuery(sql, Payment.class);
        nativeQuery.executeUpdate();
    }

//    Payment에 데이터 입력 후 입력 된 데이터 가져와서 Paylog 에 사용할 데이터 가져오는 함
    public Object getPaymentId(String productId) {
        String sql = "" +
                "select * from payment " +
                "where product_id = " +
                productId + ";";

        Query nativeQuery = em.createNativeQuery(sql, Payment.class);
        Object singlePayment = nativeQuery.getSingleResult();
        return singlePayment;
    }


//    select * from paylog inner join payment on paylog.payment_id = payment.payment_id;

}
