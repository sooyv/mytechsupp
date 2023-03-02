package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.ProductRepository;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MyPageService {


    // 회원정보검색
    private final UserRepository userRepository;

    public User updateForm(String myEmail) {
        List<User> user = userRepository.findByUserEmail(myEmail);
        return user.get(0);
    }

    // 비밀번호 일치 확인
//    public boolean checkPassword(Long UserId) {
//
//
//    }

}
