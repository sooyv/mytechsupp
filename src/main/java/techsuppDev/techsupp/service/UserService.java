package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.DTO.SignUpDTO;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Transactional
    public Long join(SignUpDTO signUpDTO) {
        System.out.println("회원가입 회원 전화번호"+ signUpDTO.getUserPhone());
        System.out.println("회원가입 회원 이름"+ signUpDTO.getUserName());
        System.out.println("회원가입 회원 email "+ signUpDTO.getEmail());

        User user = User.builder()
                .userName(signUpDTO.getUserName())
                .userEmail(signUpDTO.getEmail())
                .userPassword(passwordEncoder.encode(signUpDTO.getPassword()))  // 비밀번호 암호화
                .userPhone(signUpDTO.getUserPhone())
                .role("ROLE_USER")
                .build();

        validateDuplicateUser(user);        // 회원 중복 검증
        userRepository.save(user);
        return user.getUserId();
    }

    // 이메일 중복 검증
    private void validateDuplicateUser(User user) {
        Optional<User> findUserEmail = userRepository.findByUserEmail(user.getUserEmail());
        if (!findUserEmail.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    // 이메일 중복 검증
    public String checkId(String email, String type) {
        if (type.equals("email")) {
            Optional<User> users = userRepository.findByUserEmail(email);
            if(users.isEmpty()) {
                return "0";
            }
            return "1";
        }
        return "0";
    }


    // 회원 전체 조회
    public List<User> findUser() {
        return userRepository.findAll();
    }

    // 회원 한명 조회
    public User findOne(Long userId) {
        return userRepository.getOne(userId);
    }


    public User getUserByEmail(String userEmail) {
        Optional<User> users = userRepository.findByUserEmail(userEmail);
        if (users != null) {
            return users.get();
        }
        return null;
    }

    // 로그인
    public User login(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getUserPassword())) {
            return user;
        }
        return null;
    }


    // user 이메일 찾기
    public List<String> findUserEmail(String userName, String userPhone) {
        List<User> users = userRepository.findByUserNameAndUserPhone(userName, userPhone);
        List<String> userEmail = new ArrayList<>();
        for (User user : users) {
            String email = user.getUserEmail();
            userEmail.add(email);
        }
        if (users.size() <= 0) {
//            throw new IllegalStateException("User not found");
            userEmail = null;
            log.info("user not found");
        }

        return userEmail;
    }

    // user 비밀번호 재발급
    public void updateUserPw(String email) throws Exception {
        Optional<User> user = userRepository.findByUserEmail(email);

        if (user.isPresent()) {        // 있는 경우
            String code = mailService.sendPwMail(email);

            user.get().updatePassword(passwordEncoder.encode(code));
            userRepository.save(user.get());

        } else {
            throw new NoSuchElementException("등록되지 않은 이메일입니다.");
        }
    }

}


