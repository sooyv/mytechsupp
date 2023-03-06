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

    private final UserService userService;

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
//        List<User> users = userRepository.findByUserEmail(user.getUserEmail());
//        String userPhone = user.getUserPhone();
//        String userName = user.getUserName();
//
//        user = users.get(0);
//        user.setUserName(userName);
//        user.setUserPhone(userPhone);
        User user1 = userRepository.findByUserEmail(user.getUserEmail()).get();
        user1.setUserPhone(user.getUserPhone());
        user1.setUserName(user.getUserName());
        userRepository.save(user1);

    }
    // 비밀번호 일치 확인
//    public boolean checkPassword(Long UserId) {
//
//
//    }

}
