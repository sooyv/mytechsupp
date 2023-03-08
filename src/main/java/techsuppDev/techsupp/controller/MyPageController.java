package techsuppDev.techsupp.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.controller.form.MyPageForm;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;
import techsuppDev.techsupp.repository.ProductRepository;
import techsuppDev.techsupp.service.MyPageService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user")
public class MyPageController {

    @Autowired
    private final MyPageService myPageService;


    private final PasswordEncoder passwordEncoder;
    private ProductRepository productRepository;

    //  회원수정하기 전 비밀번호 확인
    @GetMapping("/checkPassword")
    public String checkPwdView() {
        return "mypage/checkPassword";
    }

    // 비밀번호 확인 체크
    @PostMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(@RequestParam String checkPassword, HttpSession session) throws Exception {
        System.out.println("testtt "+checkPassword);
        boolean result = false;  // 리절트값 초기화

        //기존 디비user 조회

       User user = (User) session.getAttribute("user"); // 기존 로그인 db 확인

//        String email = "tjansqja@naver.com"; //데이터베이스 JPA를 통해서 조회
        myPageService.checkPassword(user.getUserEmail());

        if (passwordEncoder.matches(checkPassword, myPageService.checkPassword(user.getUserEmail()))) {
            result = true;
        } else {
            result = false;
        }//현재 비밀번호

        return result;
    }


    //    회원정보수정페이지
    @GetMapping("/edituser") // modelandview 모델을 뷰에 던져준다는 개념임.
    public String editUser(HttpSession session, Model model) {

        String myEmail = (String) session.getAttribute("userEmail");
//        String myEmail = "tjansqja@naver.com";
        User user = myPageService.getUserEmail(myEmail);
        model.addAttribute("userinfo", user);


// model.addAttribute("userInfo.userEmail", user)
        return "mypage/editUser";
    }

    //회원정보수정페이지 수정

        @PostMapping("/edituser")
//    public String update(@ModelAttribute User user) {
//        myPageService.update(user);
////        user.setUserPassword(form.getPassword());
//        return "redirect:/mypage/";
//    }
        public ResponseEntity<String> update(MyPageForm form) {

//        System.out.println("this is my info :"+ form.getUserEmail());
        System.out.println(form.getUserName());
        System.out.println(form.getUserPhone());

        User user = new User();
        user.setUserEmail(form.getUserEmail());
        user.setUserPhone(form.getUserPhone());
        user.setUserName(form.getUserName());

            System.out.println(user.getUserName());
            System.out.println(user.getUserPhone());

//        myPageService.update(user);

        return new ResponseEntity<>("Successfully editUser", HttpStatus.OK);
    }

    // myPage 홈페이지
    @GetMapping("/mypage")
    public ModelAndView myPage() {
        ModelAndView mav = new ModelAndView("/mypage/myPage");

        return mav;
    }

    // 즐겨찾기 홈페이지

    @GetMapping("/myfavorite")
    public String favorite(HttpSession session, Model model) {
//        String userEmail = (String) session.getAttribute("userEmail"); //왜 세션값이 안받아지냐..... 계속
        long userId = 3;
        System.out.println("testtqerwq"+userId); //  테스트 결과 db값을 인젝션하면 조회 가능

        List<WishList> wishList = myPageService.findByUserId(userId);
        model.addAttribute("wishList", wishList);
        return "mypage/myFavorite";
    }
    // 투자정보 페이지

    @GetMapping("/myinvest")
    public ModelAndView invest(){
        ModelAndView mav = new ModelAndView("mypage/myInvest");
        return mav;
    }

}