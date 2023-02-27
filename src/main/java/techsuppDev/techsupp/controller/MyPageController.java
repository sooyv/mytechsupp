package techsuppDev.techsupp.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.MyPageService;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

//  회원수정하기 전 비밀번호 확인
    @GetMapping("/checkPassword")
    public String checkPwdView(){
        return "mypage/checkPassword";
    }
//
//    @PostMapping("/checkPassword")
//    public boolean checkPassword(@RequestParam String checkPassword, Model model){
//
//
//        return "mypage/checkPassword";
//    }



//    회원정보수정페이지
    @GetMapping("/edituser") // modelandview 모델을 뷰에 던져준다는 개념임.
    public String editUser(HttpSession session, Model model) {

//        String myEmail = (String) session.getAttribute("userEmail");
        String myEmail = "moonb@gmail.com";
        User user = myPageService.updateForm(myEmail);
        System.out.println("test : "+user.getUserName());
        model.addAttribute("userInfo", user);

        return "mypage/editUser";
    }

    @GetMapping("/mypage")
    public ModelAndView SignUp() {
        ModelAndView mav = new ModelAndView("/mypage/mypage");
        return mav;
    }




}
