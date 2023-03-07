package techsuppDev.techsupp.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.controller.form.MyPageForm;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;
import techsuppDev.techsupp.service.MyPageService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user")
public class MyPageController {

    @Autowired
    private final MyPageService myPageService;


    private final PasswordEncoder passwordEncoder;

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
    public ModelAndView favorite(HttpSession session) {
        ModelAndView mav = new ModelAndView("mypage/myFavorite");
        Long userId = (Long) session.getAttribute("userEmail");

        if (userId != null) {
            Optional<WishList> product = myPageService.findByUserId(userId);
            if (product.isPresent()) {
                mav.addObject("product", product.get());
                System.out.println(product.get());
            } else {
                mav.addObject("product", new WishList());
            }
        } else {
            mav.addObject("product", null);
        }

        System.out.println("test1213" + mav);
        return mav;
    }



//    model(HttpServletRequest erdsas ) {
//        HttpSession session = erdsas.getSession();
//        // 여기서 이제 겟 어트리뷰트해서 아이디 값 해서 가져올 수 있다. key value
//        // string userId = session getattribute(key).tostring()
//        // userid에 email 값이 들어감.
//
//    }
//
    //
//@GetMapping("/myfavorite")
//@ResponseBody
//public WishList getFavorite(@RequestParam Long userId) {
//    Optional<WishList> product = myPageService.findByUserWishList(userId);
//    return product.orElse(null);
//}


    // 투자정보 페이지

    @GetMapping("/myinvest")
    public ModelAndView invest(){
        ModelAndView mav = new ModelAndView("mypage/myInvest");
        return mav;
    }

}