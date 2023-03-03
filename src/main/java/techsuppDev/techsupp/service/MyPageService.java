package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;
import techsuppDev.techsupp.repository.MyPageRepository;
import techsuppDev.techsupp.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MyPageService {


    // 회원정보검색
    private final UserRepository userRepository;

    private final UserService userService;

    private final MyPageRepository myPageRepository;


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

    ////
    public List<WishList> findByUserWishList(Long userId) {
        List<WishList> product = myPageRepository.findByUserId(userId);

//        Optional<WishList> wishList1 = product;
//
//
////         db에 있는 상품을 가져와서 product1에 집어 넣고 싶다.
//        for (Product product1 : product)
//            if ()
//        }
        return product;
    }
}


//
//}

//    }
//    public User updateForm(String myEmail) {
//        Optional<User> user = userRepository.findByUserEmail(myEmail);
//        return user.get();
//    }
    // 비밀번호 일치 확인
//    public boolean checkPassword(Long UserId) {
//
//    }

    // 선택한 모든 투자제품 조회

