package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyPageService {


    // 회원정보검색
    private final UserRepository userRepository;
    public User updateForm(String myEmail) {
        Optional<User> user = userRepository.findByUserEmail(myEmail);
        return user.get();
    }

    // 비밀번호 일치 확인
//    public boolean checkPassword(Long UserId) {
//
//
//    }

}
