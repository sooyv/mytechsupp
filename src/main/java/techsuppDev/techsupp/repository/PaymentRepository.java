package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.controller.form.PaymentCountForm;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public Long getPaymentId() {
        String sql = "" +
                "select last_insert_id();";

        Query nativeQuery = em.createNativeQuery(sql);
        Long singlePayment = Long.parseLong(nativeQuery.getSingleResult().toString()) ;
        return singlePayment;
    }

//    single product 에 해당하는 payment count 필요함
    public Long getSinglePaymentCount(Long productId) {
        String sql = "" +
                "select " +
                "(select count(*) from payment " +
                "where product_id = " + productId + ") as count";
        Query nativeQuery = em.createNativeQuery(sql);
        Long paymentCount = Long.parseLong(nativeQuery.getSingleResult().toString());
        return paymentCount;
    }


//    product list 생성시 투자율 계산을 위한 투자 count value
    public ArrayList getFivePaymentCount(ArrayList<Long> fiveProductNumber) {
        String sql = "select ";

        for(int i = 0; i < fiveProductNumber.size(); i++) {

            if (i == fiveProductNumber.size() - 1) {
                String lastSql = "(select count(*) from " +
                        "(select * from payment " +
                        "where product_id = " + fiveProductNumber.get(i) + ") as num" + i +
                        ") as num" + i + "";
                sql += lastSql;
            } else if (i != 0) {
                String countSql = "(select count(*) from " +
                        "(select * from payment " +
                        "where product_id = " + fiveProductNumber.get(i) + ") as num" + i +
                        ") as num" + i + ", ";
                sql += countSql;
            } else {
                String firstSql = "(select count(*) from " +
                        "payment where product_id = " + fiveProductNumber.get(i) + ") as num0, ";
                sql += firstSql;
            }
        }

        try {
            Query nativeQuery = em.createNativeQuery(sql, PaymentCountForm.class);
            List result = nativeQuery.getResultList();
            PaymentCountForm dataFromDB = (PaymentCountForm) result.get(0);

            ArrayList paymentCount = new ArrayList();
            paymentCount.add(dataFromDB.getNum0());
            paymentCount.add(dataFromDB.getNum1());
            paymentCount.add(dataFromDB.getNum2());
            paymentCount.add(dataFromDB.getNum3());
            paymentCount.add(dataFromDB.getNum4());
            return paymentCount;
        } catch (Exception e) {
            Query nativeQuery = em.createNativeQuery(sql, PaymentCountForm.class);
            List result = nativeQuery.getResultList();
            PaymentCountForm dataFromDB = (PaymentCountForm) result.get(0);

            ArrayList paymentCount = new ArrayList();
            paymentCount.add(dataFromDB.getNum0());
            paymentCount.add(dataFromDB.getNum1());
            paymentCount.add(dataFromDB.getNum2());
            paymentCount.add(dataFromDB.getNum3());
            paymentCount.add(dataFromDB.getNum4());
            return paymentCount;
        }
    }

}
