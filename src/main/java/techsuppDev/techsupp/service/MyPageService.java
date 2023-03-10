package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.Paylog;
import techsuppDev.techsupp.domain.*;
import techsuppDev.techsupp.repository.MyPageRepository;
import techsuppDev.techsupp.repository.PayLogRepository;
import techsuppDev.techsupp.repository.PaymentRepository;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyPageService {


    // 회원정보검색
    private final UserRepository userRepository;
    private final UserService userService;
    private final MyPageRepository myPageRepository;

    private final PayLogRepository payLogRepository;

    private final PaymentRepository paymentRepository;

    // 이메일 조회
    public User getUserEmail(String myEmail) {
        Optional<User> user = userRepository.findByUserEmail(myEmail);
        if (user != null) {
            return user.get();
        }
        return null;
    }

    // 비밀번호 확인
    public String checkPassword(String email) {
        return userService.getUserByEmail(email).getUserPassword();
    }

    // 회원 업데이트
    public void update(User user) {
        User user1 = userRepository.findByUserEmail(user.getUserEmail()).get();
        user1.setUserPhone(user.getUserPhone());
        user1.setUserName(user.getUserName());
        userRepository.save(user1);
    }

    //// 로그인한 회원의 즐겨찾기 목록

    public List<WishList> findByUserId(Long userId) {
        List<WishList> wishList = myPageRepository.findByUserId(userId);
        return wishList;
    }

//
//    // 현재 진행중인 투자 목록
//
//    // 결제 내역 조회
//    public Payment getPaymentDetails(String paymentId) {
//        return paymentRepository.getPaymentId(paymentId).orElse(null);
//    }
//
//    // 결제 취소
//    public void cancelPayment(String paymentId) {
//        Payment payment = paymentRepository.getPaymentId(paymentId).orElse(null);
//        if (payment == null) {
//            throw new IllegalArgumentException("Invalid Payment Id");
//        }
//
//        // 결제 상태 변경
//        payment.setPaylogStatus(PaylogStatus.REFUND);
//        paymentRepository.save(payment);
//
//        // 결제 로그 저장
//        Paylog payLog = new Paylog();
//        payLog.setPaymentId(paymentId);
//        payLog.setPaylogStatus(PaylogStatus.REFUND);
//        payLog.setUserEmail(payment.getUserEmail());
//        payLogRepository.savePaylog(payLog);
//    }
}
