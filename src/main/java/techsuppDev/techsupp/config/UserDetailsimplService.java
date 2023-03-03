package techsuppDev.techsupp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.UserRepository;

import java.util.Optional;


@Service
public class UserDetailsimplService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        Optional<User> userOptional = userRepository.findByUserEmail(email);
//
//        if (userOptional.isPresent()) {
//            throw new UsernameNotFoundException("사용자 없음");
//        }
//        User user = userOptional.get();
//        System.out.println("loadUserByUsername 실행");
//        System.out.println("user 이름은 : " + user.getUserName());         // 왜 안찍혀?
//
//        return new UserDetailsimpl(user);


        Optional<User> userOp = userRepository.findByUserEmail(email);
        System.out.println("email : " +email);
        System.out.println(userOp.get().getUserId());
        System.out.println("name : " + userOp.get().getUserName());
        System.out.println("role : " + userOp.get().getRole());

        User user = userOp.get();

//        if(user != null) {                             // userOp가 존재하지 않으면
//            System.out.println("user 없음");
//            System.out.println(userOp.get());
//            throw new UsernameNotFoundException(email);      // UsernameNotFoundException 발생
//        }
//        System.out.println("user 가져옴");
//        return new UserDetailsimpl(userOp.get());


        if(user != null) {                             // userOp가 존재하지 않으면
            System.out.println("user 가져옴");
            return new UserDetailsimpl(userOp.get());
        }
        System.out.println("user 없음");
        System.out.println(userOp.get());
        throw new UsernameNotFoundException(email);      // UsernameNotFoundException 발생
    }
}
