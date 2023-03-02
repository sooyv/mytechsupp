package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import techsuppDev.techsupp.controller.form.UserForm;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.UserService;

import javax.servlet.http.*;
import javax.validation.Valid;
import java.io.*;
import java.math.BigInteger;
import java.net.BindException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;


//@Controller
@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    // 로그인 창
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/login/login");
        mav.addObject("userForm",new UserForm());
        return mav;
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
    @PostMapping("/user/signup")
    public ResponseEntity<String> signUpUser(@RequestParam("userName") String userName, @RequestParam("email") String email,
                                             @RequestParam("password") String password, @RequestParam("userPhone") String userPhone) {
        System.out.println(userName);
        System.out.println(email);
        System.out.println(password);
        System.out.println(userPhone);

        User user = new User();
        user.setUserName(userName);
        user.setUserEmail(email);
        user.setUserPassword(password);
        user.setUserPhone(userPhone);

        userService.join(user);

//        ModelAndView mav = new ModelAndView("redirect:/");
//        return mav;
        return new ResponseEntity<>("Successfully Registered", HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/user/login")
    public ModelAndView login(@Valid @ModelAttribute UserForm userForm, HttpSession session) {
//    public ModelAndView login(@RequestParam String email, @RequestParam String password, HttpSession session) {

        String formEmail = userForm.getEmail();
        String formPassword = userForm.getPassword();

        User user = userService.login(formEmail, formPassword);

        if (user != null && user.getUserEmail().equals(formEmail)) {
            session.setAttribute("loginEmail", user.getUserEmail());
            System.out.println(user.getUserName() + "로그인 완료");

        } else {
//            return new ResponseEntity<>("Login faild", HttpStatus.BAD_REQUEST)
            System.out.println("존재하지 않는 회원정보");
            ModelAndView mav = new ModelAndView("redirect:/login");
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:/");
        return mav;
    }

    // 로그아웃
    @PostMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "/";
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
