package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.Paylog;
import techsuppDev.techsupp.controller.form.PaymentForm;
import techsuppDev.techsupp.domain.Payment;
import techsuppDev.techsupp.repository.PayLogRepository;
import techsuppDev.techsupp.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {
    public final PaymentRepository paymentRepository;

    public final PayLogRepository payLogRepository;

    public void savePay(PaymentForm payment) {
        paymentRepository.savePayment(payment);
    }

    public Object getSinglePayment(String productId) {
        return paymentRepository.getPaymentId(productId);
    }

    public void savePaylog(Paylog paylog) {
        payLogRepository.savePaylog(paylog);
    }
}
