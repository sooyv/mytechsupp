package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.UserDTO;
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
    private final UserRepository userRepository;
    private final UserService userService;
    private final MyPageRepository myPageRepository;
    private final PayLogRepository payLogRepository;
    private final PaymentRepository paymentRepository;
    private final PasswordEncoder passwordEncoder;

    // 비밀번호 확인
//    public String checkPassword(String email) {
//        return userService.getUserByEmail(email).getUserPassword();
//    }
    public boolean checkPassword(String userEmail, String checkPassword) {
        Optional<User> user = userRepository.findByUserEmail(userEmail);
        return user != null && passwordEncoder.matches(checkPassword, user.get().getUserPassword());
    }

    // 회원 업데이트
    public void userUpdate(UserDTO userDTO) {
        User updateUser = userService.getUserByEmail(userDTO.getUserEmail());
        updateUser.updateUsername(userDTO.getUserName());
        updateUser.updatePhone(userDTO.getUserPhone());
        userRepository.save(updateUser);
    }

    // 비밀번호 업데이트
    public void changePassword(User user) {
        user.updatePassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user);
    }


    // 로그인한 회원의 즐겨찾기 목록
    public List<WishList> findByUserEmail(String userEmail) {
        List<WishList> wishList = myPageRepository.findByUserEmail(userEmail);
        return wishList;
    }


    // 현재 진행중인 투자 목록
//
//    // 결제 내역 조회
//    public Paylog getPaylog(Long paylogId) {
//        return payLogRepository.getPaylogId(paylogId);
//    }
//
//    // 결제 취소
//    public void cancelPaylog(Long cancelPaylog) {
//        Paylog paylog = (Paylog) payLogRepository.getPaylogId(cancelPaylog);
//
//
//    }
//
//    // 결제 상태 변경
//    public void paylogUpdate(PaylogStatus paylogStatus) {
//    PaylogStatus paylogStatus1 =
//        paylogStatus.setPaylogStatus(PaylogStatus.REFUND);
//    paymentRepository.save(paylogStatus);  // jpa형식으로 save하면 바로 나오는데
//    }
}
