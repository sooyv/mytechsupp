package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.config.UserDetailsimpl;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.MailService;
import techsuppDev.techsupp.service.UserService;

import javax.servlet.http.*;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;

//@Controller
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final MailService mailService;


    // 로그인 창
    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        if (userName != null) {    // 세션이 있으면 "redirect:/" 로그인 페이지 접근 차단
            ModelAndView mav = new ModelAndView("redirect:/");
            return mav;

        } else {                  // 세션이 없으면 로그인 페이지 접근 가능
            ModelAndView mav = new ModelAndView("/login/login");
            return mav;
        }
    }

    // 회원가입 창
    @GetMapping("/signup")
    public ModelAndView Signup() {
        ModelAndView mav = new ModelAndView("/signup/signup");
        return mav;
    }

    // 회원가입 이메일 중복 검사
    @PostMapping("/signup/checkid")
    @ResponseBody
    public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {
        String result = userService.checkId(id, type);
        if(result != null && result.equals("0")) {
            return 0;
        } else {
            return 1;
        }
    }

    // 회원가입
    @PostMapping("/member/signup")
    public ResponseEntity<String> signUpUser(@RequestParam("userName") String userName, @RequestParam("email") String email,
                                             @RequestParam("authNum") String authNum, @RequestParam("password") String password,
                                             @RequestParam("checkPassword") String checkPassword, @RequestParam("userPhone") String userPhone,
                                             HttpServletRequest request) {

        HttpSession authSession = request.getSession();
        String code = (String) authSession.getAttribute("authCode");

       if (!password.equals(checkPassword)) {
           return new ResponseEntity<>("password", HttpStatus.BAD_REQUEST);
       }

       if (code == null) {
           return new ResponseEntity<>("codeNull", HttpStatus.BAD_REQUEST);
       } else if (!authNum.equals(code)) {
           return new ResponseEntity<>("authNum", HttpStatus.BAD_REQUEST);
       }

        User user = new User();
        user.setUserName(userName);
        user.setUserEmail(email);
        user.setUserPassword(password);
        user.setUserPhone(userPhone);
        user.setRole("ROLE_USER");          // 무조건 role컬럼엔 ROLE_USER으로
        log.info(userName);

        userService.join(user);

        return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
    }


    // 이메일 인증
    @PostMapping("/signup/mailcheck")
    @ResponseBody
    public ResponseEntity<String> mailCheck(@RequestParam String email, HttpSession session) throws Exception {
        System.out.println("이메일 인증요청");
        System.out.println("인증 이메일: " + email);

        String code = null;
        try {
            code = mailService.sendMail(email);
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("인증메일 전송 실패");
        }

        session.setAttribute("authCode", code);
        session.setMaxInactiveInterval(180);        // 3분동안만 회원가입 가능

        return ResponseEntity.ok(code);
    }


    // 이메일 인증번호 확인
//    @PostMapping("/mail/check/auth")
//    public int mailAuthCheck(@RequestParam String emailAuth, HttpServletRequest request) {
//        HttpSession authSession = request.getSession();
//        String code = (String) authSession.getAttribute("authCode");
//
//        System.out.println("입력한 인증번호: " + emailAuth);
//        System.out.println("세션에 저장한 인증번호: " + code);
//
//        if (code != null && code.equals(emailAuth)) {
//            // 인증이 완료되었습니다.
//            return 0;
//        } else if (code == null) {      // 세션이 만료
//            // 세션이 만료되었습니다.
//            return 1;
//        } else {
//            // 인증 번호가 틀렸습니다.
//            return 2;
//        }
//    }


    // 로그인과 로그아웃의 url spring Security에 의해서 관리
//    로그인
//    @PostMapping("/member/login")                                         // HttpServletRequest
//    public ModelAndView login(@Valid @ModelAttribute UserForm userForm, HttpSession session) {
////    public ModelAndView login(@RequestParam String email, @RequestParam String password, HttpSession session) {
//
//        String formEmail = userForm.getEmail();
//        String formPassword = userForm.getPassword();
//
//        User user = userService.login(formEmail, formPassword);
//
//        if (user != null && user.getUserEmail().equals(formEmail)) {
//            session.setAttribute("loginUserName", user.getUserName());
//
////            HttpSession session = request.getSession(true);
////            log.info(session.getId());
//
//            System.out.println(user.getUserName() + " 로그인 완료");
//
//        } else {
////            return new ResponseEntity<>("Login faild", HttpStatus.BAD_REQUEST)
//            System.out.println("존재하지 않는 회원정보");
//            ModelAndView mav = new ModelAndView("redirect:/login");
//            return mav;
//        }
//
//        ModelAndView mav = new ModelAndView("redirect:/");
//        return mav;
//    }

//    로그아웃
//    @PostMapping("/member/logout")
//    public String logout(HttpSession session) {
//        session.invalidate();           // 세션 null 여부 검사
//        return "/";
//    }

    // 세션
    @GetMapping("/member/loginsuccess")
    public ModelAndView loginSuccess(HttpSession session, @AuthenticationPrincipal UserDetailsimpl userDetails){

        if(userDetails != null) {
            User user = userDetails.getUser();
//            System.out.println("----------------homeController------------------");
            session.setAttribute("userId", user.getUserId());          // userId 세션에 저장
            session.setAttribute("userEmail", user.getUserEmail());    // email 세션에 저장
            session.setAttribute("userName", user.getUserName());      // name 세션에 저장
            session.setAttribute("userPhone", user.getUserPhone());    // phone 세션에 저장
            session.setAttribute("userRole", user.getRole());          // userRole 세션에 저장
//            session.setAttribute("user", user);

        }
        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }

    // 세션의 user 정보를 받아오기 확인
//            public ResponseEntity getUserSessions(HttpServletRequest req) {
//            HttpSession loginSession = req.getSession();
//            String userEmail = loginSession.getAttribute("userEmail").toString();
//            String userName = (String) loginSession.getAttribute("userName");
//            String userPhone = (String) loginSession.getAttribute("userPhone");
//            String userRole = (String) loginSession.getAttribute("userRole");
//            Long userId = (Long) loginSession.getAttribute("userId");
//
//                System.out.println(userEmail);
//                System.out.println(userName);
//                System.out.println(userRole);
//                System.out.println(userPhone);
//                System.out.println(userId);
//
//            return ResponseEntity.ok().body(userService.getUserByEmail(userEmail));
//        }

    // Access Denied Page
    @GetMapping("access/denied")
    public ModelAndView denied() {
        ModelAndView mav = new ModelAndView("/accessdenied/denied");
        return mav;
    }

    // 아이디 비밀번호 찾기 page
    @GetMapping("/find/member")
    public ModelAndView findMember(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("userName");

        if (userName != null) {    // 세션이 있으면 "redirect:/" 아이디, 비밀번호 찾기 페이지 접근 차단
            ModelAndView mav = new ModelAndView("redirect:/");
            return mav;

        } else {                  // 세션이 없으면 아이디, 비밀번호 찾기 페이지 접근 가능
            ModelAndView mav = new ModelAndView("/login/finduser");
            return mav;
        }
    }

    // 아이디 찾기
    @ResponseBody
    @PostMapping("/find/member/id")
    public List<String> findUserId(@RequestParam("userName") String userName, @RequestParam("userPhone") String userPhone) {

        System.out.println(userName);
        System.out.println(userPhone);

        List<String> userEmail = userService.findUserEmail(userName, userPhone);
        return userEmail;
    }


    // 비밀번호 찾기
    @PostMapping("/find/member/pw")
    public String findUserPw(@RequestParam("userEmail") String userEmail) throws Exception {

        try {
            System.out.println(userEmail);
            userService.updateUserPw(userEmail);
            return "비밀번호 재설정 메일을 전송했습니다. 이메일을 확인해 주세요";

        } catch (NoSuchElementException e) {
            return "등록되지 않은 이메일 주소입니다. 다시 입력해주세요.";

        } catch (Exception e) {
            return "비밀번호 재설정에 실패했습니다. 다시 시도해주세요.";
        }
    }







    // --------- 네이버 로그인 test -------------------------------------------------------------------------
    @GetMapping("test")
    public String test(HttpSession session, HttpServletResponse response) throws IOException {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String redirectURI = URLEncoder.encode("http://localhost:8080/navercallback" , "UTF-8");
        SecureRandom random = new SecureRandom();
        String state = new BigInteger(130, random).toString();
        String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectURI
                + "&state=" + state;
        session.setAttribute("state", state);


//        String html = "<html><body><a href='"+apiURL +"'>aaa</a></body></html>";

        return "redirect:"+apiURL;

    }

    @GetMapping("navercallback")
    public void naverCallBack(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String clientId = "";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "9X_i0NyIfC";//애플리케이션 클라이언트 시크릿값";
        String error = request.getParameter("error");
        String error_desc = request.getParameter("error_description");
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        System.out.println(code+state);
        String redirectURI = URLEncoder.encode("http://localhost:8080/navercallback", "UTF-8");
        String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                + "&client_id=" + clientId
                + "&client_secret=" + clientSecret
                + "&redirect_uri=" + redirectURI
                + "&code=" + code
                + "&state=" + state;
        String accessToken = "";
        String refresh_token = "";
        try {
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            PrintWriter out = response.getWriter();
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuilder res = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                res.append(inputLine);
            }
            br.close();
            if (responseCode == 200) {
                out.println(res.toString());
            }
        } catch (Exception e) {
            // Exception 로깅
        }
    }
}
