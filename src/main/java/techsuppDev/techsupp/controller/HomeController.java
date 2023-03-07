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

//        User user = userDetails.getUser(); 왜 null?

        if(userDetails != null) {
            System.out.println("----------------homeController------------------");
            session.setAttribute("userEmail", userDetails.getUser().getUserEmail());    // email 세션에 저장
            session.setAttribute("userName", userDetails.getUser().getUserName());      // name 세션에 저장
            session.setAttribute("userPhone", userDetails.getUser().getUserPhone());    // phone 세션에 저장
            session.setAttribute("userRole", userDetails.getUser().getRole());          // userRole 세션에 저장
        }
        return "main/main";
    }

    // 세션 -> 로그인페이지. 자동으로 메인페이지로 : logout 시에는 로그인으로 갈 수 있도록

//    @GetMapping("/")
//    public String main() {
//        return "/main/main";
//    }
}
