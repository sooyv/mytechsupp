package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.controller.form.MyPageForm;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;
import techsuppDev.techsupp.repository.ProductRepository;
import techsuppDev.techsupp.repository.UserRepository;
import techsuppDev.techsupp.service.MyPageService;
import techsuppDev.techsupp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user")
public class MyPageController {

    private final MyPageService myPageService;
    private final UserService userService;
    private ProductRepository productRepository;

    //  회원수정하기 전 비밀번호 확인
    @GetMapping("/checkpassword")
    public String checkPwdView(@RequestParam(name = "path") String path, HttpServletRequest request) {
        System.out.println("mypageController checkpassword : " + path);
        HttpSession session = request.getSession();
        session.setAttribute("path", path);
        return "mypage/checkPassword";
    }

    // 비밀번호 확인 체크
    @PostMapping("/checkpassword")
    @ResponseBody
    public ResponseEntity<String> checkPassword(@RequestParam(name = "checkPassword") String checkPassword,
                                                HttpServletRequest request) throws Exception {
//        boolean result = false;  // 리절트값 초기화

        // 현재 세션 회원 확인
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        String redirectPath = (String) session.getAttribute("path");

        User user = userService.getUserByEmail(userEmail); // 기존 로그인 db 확인
        System.out.println("user: " + user);
//        String email = "tjansqja@naver.com"; //데이터베이스 JPA를 통해서 조회
        Boolean result = myPageService.checkPassword(user.getUserEmail(), checkPassword);


        System.out.println("-------------------------------------");
        System.out.println("redierctPath mypagecontroller: " + redirectPath);
        System.out.println("-------------------------------------");

        if (result) {
            session.setAttribute("checkPasswordOk", true);
            return new ResponseEntity<>(redirectPath, HttpStatus.OK);
        } else {
            session.setAttribute("checkPasswordOk", false);
            return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
        }
//        return result;

//        if (passwordEncoder.matches(checkPassword, findUserPassword)) {
//            result = true;
//            session.setAttribute("checkPasswordOk" , "OK");
//        } else {
//            result = false;
//        } //현재 비밀번호
//        return result;
    }


    // 비밀번호 확인 두번 째

    //  회원수정하기 전 비밀번호 확인
//    @GetMapping("/checkPassword1")
//    public String checkPwdView1() {
//
//        return "mypage/checkPassword1";
//    }

    // 비밀번호 확인 체크
//    @PostMapping("/checkPassword1")
//    @ResponseBody
//    public boolean checkPassword1(@RequestParam(name = "checkPassword1") String checkPassword, HttpServletRequest request) throws Exception {
//
//        System.out.println("testt1qt "+checkPassword);
//        boolean result = false;  // 리절트값 초기화
//
//        //기존 디비user 조회
//        HttpSession session = request.getSession();
//        System.out.println("zzzz"+session); // 세션은 받아오는데 유저 이메일을 못받아오는듯? 세션의
//        String userEmail = (String) session.getAttribute("userEmail");
//        User user = myPageService.getUserEmail(userEmail); // 기존 로그인 db 확인
//        System.out.println("gfhhhg"+user);
////        String email = "tjansqja@naver.com"; //데이터베이스 JPA를 통해서 조회
//        myPageService.checkPassword(user.getUserEmail());
//
//        if (passwordEncoder.matches(checkPassword, myPageService.checkPassword(user.getUserEmail()))) {
//            result = true;
//            session.setAttribute("checkPasswordOk" , "OK");
//        } else {
//            result = false;
//        }//현재 비밀번호
//        return result;
//    }


    // 회원정보수정페이지
    @GetMapping("/edituser")
    public String editUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Boolean checkPasswordOk = (Boolean) session.getAttribute("checkPasswordOk");

        // 기존
//        if (checkPasswordOk == null) {
//            return "redirect:/user/mypage";
//        }
//        String findUserMail = (String) session.getAttribute("userEmail");
//        User user = userService.getUserByEmail(findUserMail);

//        model.addAttribute("userinfo", user);
//        return "mypage/editUser";

        // 변경
        if (checkPasswordOk != null && checkPasswordOk) {
            String findUserMail = (String) session.getAttribute("userEmail");
            User user = userService.getUserByEmail(findUserMail);
            model.addAttribute("userinfo", user);
            return "mypage/editUser";
        } else {
            return "redirect:/mypage";
        }
    }

    @PostMapping("/edituser")
    public String userUpdate(@ModelAttribute("userinfo") User user, HttpSession session) {
        User findUser = userService.getUserByEmail(user.getUserEmail());

        findUser.setUserName(user.getUserName());
        findUser.setUserPhone(user.getUserPhone());
        myPageService.userUpdate(findUser);
        session.removeAttribute("checkPasswordOk");
        return "redirect:/user/mypage";
    }

    //비밀번호 변경페이지
    @GetMapping("/editpassword")
    public String editPassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Boolean checkPasswordOk = (Boolean) session.getAttribute("checkPasswordOk");

//        if (session.getAttribute("checkPasswordOk") == null) {
//            return  "redirect:/user/mypage";
//        }
//        String findUserEmail = (String) session.getAttribute("userEmail");
//        User user = userService.getUserByEmail(findUserEmail);
//        model.addAttribute("userinfo", user);

        if (checkPasswordOk != null && checkPasswordOk) {
            String findUserMail = (String) session.getAttribute("userEmail");
            User user = userService.getUserByEmail(findUserMail);
            model.addAttribute("userinfo", user);
            return "mypage/editPassword";
        } else {
            return "redirect:/mypage";
        }
//        return "mypage/editPassword";
    }

    @PostMapping("/editpassword")
    public String changePassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String findUserEmail = (String) session.getAttribute("userEmail");
        User user = userService.getUserByEmail(findUserEmail);
        user.setUserPassword(password);
        myPageService.changePassword(user);
        session.removeAttribute("checkPasswordOk");
        return "redirect:/user/mypage";
    }


    // myPage 홈페이지
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String findUserEmail = (String) session.getAttribute("userEmail");
        User user = userService.getUserByEmail(findUserEmail);
        model.addAttribute("userinfo", user);
        return "mypage/myPage";
    }

    // 즐겨찾기 홈페이지
    @GetMapping("/myfavorite")
    public String favorite(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");

        List<WishList> wishList = myPageService.findByUserEmail(userEmail);
        model.addAttribute("wishList", wishList);
        return "mypage/myFavorite";
    }


// 투자정보 페이지
//
//    @GetMapping("/myinvest")
//    public ModelAndView invest(Long paymentId){
//        ModelAndView mav = new ModelAndView("mypage/myInvest");
////        Paylog paylog = myPageService.findByUserId();
//        return mav;
//    }
}