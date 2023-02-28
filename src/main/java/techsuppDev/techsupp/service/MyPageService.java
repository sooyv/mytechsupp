package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MyPageService {


    // 회원정보검색
    private final UserRepository userRepository;

    private final UserService userService;

    public User getUserEmail(String myEmail) {
        List<User> user = userRepository.findByUserEmail(myEmail);
        if (user != null && user.size() !=0) {
            return user.get(0);
        }
        return null;
    }

    public String checkPassword(String email) {
        return userService.getUserByEmail(email).getUserPassword();
    }

    public void update(User user) {
//        List<User> users = userRepository.findByUserEmail(user.getUserEmail());
//        String userPhone = user.getUserPhone();
//        String userName = user.getUserName();
//
//        user = users.get(0);
//        user.setUserName(userName);
//        user.setUserPhone(userPhone);
        User user1 = userRepository.findByUserEmail(user.getUserEmail()).get(0);
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
