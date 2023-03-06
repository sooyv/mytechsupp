package techsuppDev.techsupp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;


@Service
public class UserDetailsimplService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOp = userRepository.findByUserEmail(email);
        System.out.println("--------------------loadUserByUsername--------------------");
        System.out.println("email : " +email);
        System.out.println(userOp.get().getUserId());
        System.out.println("name : " + userOp.get().getUserName());
        System.out.println("role : " + userOp.get().getRole());

        User user = userOp.get();

        if(user != null) {
            System.out.println("user 가져옴");
            return new UserDetailsimpl(userOp.get());
        }

        System.out.println("user 없음");                  // userOp가 존재하지 않으면
        System.out.println(userOp.get());
        throw new UsernameNotFoundException(email);      // UsernameNotFoundException 발생
    }
}
