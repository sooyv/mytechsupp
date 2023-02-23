package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.respository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(User user) {
        String userName = user.getUserName();
        String userEmail = user.getUserEmail();
        String userPassword = user.getUserPassword();
        // 사용자 비밀번호 암호화. (시큐리티 BCryptPasswordEncoder/ 빈등록 객체)
//        user.setPassword(passwordEncoder.encode(password));
        String userPhone = user.getUserPhone();
        user = user.createUser(userName, userEmail, userPassword, userPhone);
        // 회원 중복 검증
        validateDuplicateUser(user);
        userRepository.save(user);
        return user.getUserId();
    }


    // 아이디 중복검증
    private void validateDuplicateUser(User user) {
        List<User> findUserEmail = userRepository.findByUserEmail(user.getUserEmail());
        if (!findUserEmail.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }






}


