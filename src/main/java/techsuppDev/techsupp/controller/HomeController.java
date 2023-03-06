package techsuppDev.techsupp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import techsuppDev.techsupp.config.UserDetailsimpl;
import techsuppDev.techsupp.domain.User;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String main(HttpSession session, @AuthenticationPrincipal UserDetailsimpl userDetails) {

        if(userDetails != null) {
            System.out.println("----------------homeController------------------");
            session.setAttribute("userEmail", userDetails.getUser().getUserEmail());

            System.out.println("udi 확인" + userDetails.getUsername());

            System.out.println("test : " + userDetails.getUsername());
            System.out.println("homeController role: " + userDetails.getAuthorities());
        }
        return "main/main";
    }

//    @GetMapping("/")
//    public String main() {
//        return "/main/main";
//    }

//    @GetMapping("/check?session")
//    public ResponseEntity<String> checkSession(@AuthenticationPrincipal UserDetailsimpl userDetailsimpl) {
//        if (userDetailsimpl == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok().build();
//    }
}
