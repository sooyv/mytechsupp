package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user")
public class MyPageController {

    @Autowired
    private final MyPageService myPageService;
    private final PasswordEncoder passwordEncoder;
    private ProductRepository productRepository;

    //  회원수정하기 전 비밀번호 확인
    @GetMapping("/checkpassword")
    public String checkPwdView() {
        return "mypage/checkPassword";
    }

    // 비밀번호 확인 체크
    @PostMapping("/checkpassword")
    @ResponseBody
    public boolean checkPassword(@RequestParam(name = "checkPassword") String checkPassword, HttpServletRequest request) throws Exception {

        System.out.println("checkPassword: "+checkPassword);
        boolean result = false;  // 리절트값 초기화

        //기존 디비user 조회
        HttpSession session = request.getSession();
        System.out.println("zzzz"+session); // 세션은 받아오는데 유저 이메일을 못받아오는듯? 세션의
        String userEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(userEmail); // 기존 로그인 db 확인
        System.out.println("gfhhhg"+user);
//        String email = "tjansqja@naver.com"; //데이터베이스 JPA를 통해서 조회
        myPageService.checkPassword(user.getUserEmail());

        if (passwordEncoder.matches(checkPassword, myPageService.checkPassword(user.getUserEmail()))) {
            result = true;
            session.setAttribute("checkPasswordOk" , "OK");
        } else {
            result = false;
        }//현재 비밀번호
        return result;
    }


    // 비밀번호 확인 두번 째

    //  회원수정하기 전 비밀번호 확인
    @GetMapping("/checkPassword1")
    public String checkPwdView1() {

        return "mypage/checkPassword1";
    }

    // 비밀번호 확인 체크
    @PostMapping("/checkPassword1")
    @ResponseBody
    public boolean checkPassword1(@RequestParam(name = "checkPassword1") String checkPassword, HttpServletRequest request) throws Exception {

        System.out.println("testt1qt "+checkPassword);
        boolean result = false;  // 리절트값 초기화

        //기존 디비user 조회
        HttpSession session = request.getSession();
        System.out.println("zzzz"+session); // 세션은 받아오는데 유저 이메일을 못받아오는듯? 세션의
        String userEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(userEmail); // 기존 로그인 db 확인
        System.out.println("gfhhhg"+user);
//        String email = "tjansqja@naver.com"; //데이터베이스 JPA를 통해서 조회
        myPageService.checkPassword(user.getUserEmail());

        if (passwordEncoder.matches(checkPassword, myPageService.checkPassword(user.getUserEmail()))) {
            result = true;
            session.setAttribute("checkPasswordOk" , "OK");
        } else {
            result = false;
        }//현재 비밀번호
        return result;
    }




    //    회원정보수정페이지
    @GetMapping("/edituser")
    public String editUser(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        if(session.getAttribute("checkPasswordOk") == null){
            return "redirect:/user/mypage";
        }
        String myEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(myEmail);

        model.addAttribute("userinfo", user);
        return "mypage/editUser";
    }

    @PostMapping("/edituser")
    public String userUpdate(@ModelAttribute("userinfo") User user,HttpSession session) {
        System.out.println("tes22t"+user.getUserPhone());
        System.out.println("tes22t"+user.getUserName());
        System.out.println("tes22t"+user.getUserEmail());
        System.out.println("tes22t"+user.getRole());
        System.out.println("tes22t"+user.getUserPassword());

        User user1 = myPageService.getUserEmail(user.getUserEmail());

        user1.setUserName(user.getUserName());
        user1.setUserPhone(user.getUserPhone());
        myPageService.userUpdate(user1);
        session.removeAttribute("checkPasswordOk");
        return "redirect:/user/mypage";


    }



    //비밀번호 변경페이지
    @GetMapping("/editpassword")
    public String editPassword(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        if(session.getAttribute("checkPasswordOk") == null){
            return  "redirect:/user/mypage";
        }
        String myEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(myEmail);
        model.addAttribute("userinfo", user);
        return "mypage/editPassword";
    }

    @PostMapping("/editpassword")
    public String changePassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String myEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(myEmail);
        user.setUserPassword(password);
        myPageService.changePassword(user);
        session.removeAttribute("checkPasswordOk");
        return "redirect:/user/mypage";
    }



    // myPage 홈페이지
    @GetMapping("/mypage")
    public String myPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
    String myEmail = (String) session.getAttribute("userEmail");
        User user = myPageService.getUserEmail(myEmail);
        model.addAttribute("userinfo", user);
        return "mypage/myPage";
    }

    // 즐겨찾기 홈페이지
    @GetMapping("/myfavorite")
    public String favorite(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        System.out.println("tset"+session);
//        String userEmail = (String)session.getAttribute("userEmail");
        String userEmail = (String) session.getAttribute("userEmail"); //왜 세션값이 안받아지냐..... 계속
        System.out.println("testtqerwq"+userEmail); //  테스트 결과 db값을 인젝션하면 조회 가능

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