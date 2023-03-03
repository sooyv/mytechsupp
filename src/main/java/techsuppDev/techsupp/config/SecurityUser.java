//package techsuppDev.techsupp.config;
//
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import techsuppDev.techsupp.domain.User;
//
//public class SecurityUser extends UserDetails {
//
//    private User user;
//
//    // user라고 정의 된 것과 반드시 매핑이 되어야함.
//    public SecurityUser(User user) {
//        // 시큐리티 user 구현
//        super(user.getUserId(), user.getUserName(), user.getUserEmail(),
//                user.getUserPassword(), user.getUserPhone(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
//
//        this.user = user;
//    }
//
//}
