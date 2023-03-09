package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.Paylog;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;
import techsuppDev.techsupp.repository.PayLogRepository;
import techsuppDev.techsupp.repository.PaymentRepository;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public final PaymentRepository paymentRepository;

    public final PayLogRepository payLogRepository;

//    payment table에 데이터 입력
    public void savePayment(PaymentForm payment) {
        paymentRepository.savePayment(payment);
    }

//    payment table에 데이터 입력 된 것 검증 후 paylog에 사용할 데이터 return
    public Object getSinglePayment(String productId) {
        return paymentRepository.getPaymentId(productId);
    }

//    paylog table에 데이터 입력
    public void savePaylog(Paylog paylog) {
        payLogRepository.savePaylog(paylog);
    }

//    과거 paylog가 존재하는지 확인하는 것
    public String checkPaylogHistory(String userEmail) {
        System.out.println("checkpayloghistroahir");
        if (payLogRepository.checkPaylog(userEmail) == null) {
            System.out.println("null 일때 나오는 메세지");
            return "log does not exist";
        } else {
            System.out.println("null 이 아닐때 나오는 메세지");
            return "log exist";
        }
    }

}
