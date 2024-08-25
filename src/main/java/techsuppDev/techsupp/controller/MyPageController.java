package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.DTO.UserDTO;
import techsuppDev.techsupp.controller.form.MyPageForm;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.domain.WishList;
import techsuppDev.techsupp.repository.ProductRepository;
import techsuppDev.techsupp.repository.UserRepository;
import techsuppDev.techsupp.service.MyPageService;
import techsuppDev.techsupp.service.QuestionService;
import techsuppDev.techsupp.service.UserService;

import javax.persistence.MapsId;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping(value = "/user")
public class MyPageController {

    private final MyPageService myPageService;
    private final UserService userService;
    private final QuestionService questionService;


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

    }


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

//    public String userUpdate(@ModelAttribute("userinfo") User user, HttpSession session) {
    @PostMapping("/edituser")
    public String userUpdate(@ModelAttribute("userinfo") UserDTO userDTO, HttpSession session) {

//        findUser.setUserName(userDTO.getUserName());
//        findUser.setUserPhone(userDTO.getUserPhone());
        myPageService.userUpdate(userDTO);
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
        user.updatePassword(password);
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


    @GetMapping("/myinquiry")
    public String myInquiry(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        List<QuestionDTO> questionList = questionService.getQuestionsByUserId(userId);

        model.addAttribute("questionList", questionList);
        return "mypage/myInquiry";
    }

    // mypage 문의사항 수정
    @GetMapping("/inquiry/edit/{questionId}")
    public String editInquiryPage(@PathVariable("questionId") Long questionId, Model model, RedirectAttributes redirectAttributes) {

        QuestionDTO question = questionService.findByIdWithAnswer(questionId);
        String status = String.valueOf(question.getQuestionStatus());

        // 상태가 'PENDING'인 경우: 수정 페이지 접속 가능
        if ("PENDING".equals(status)) {
            System.out.println("question filename 확인 : " + question.getOriginalFileName());
            model.addAttribute("question", question);
            return "/mypage/editInquiry";

        // 상태가 'PENDING'이 아닌 경우: 에러 메시지와 함께 리다이렉트
        } else {
            redirectAttributes.addFlashAttribute("error", "'답변대기'상태일때만 수정이 가능합니다.");
            return "redirect:/mypage/myinquiry";
        }
    }

    // mypage 문의사항 수정 post
    @PostMapping("/inquiry/edit/{questionId}")
    public String editInquiry(@PathVariable("questionId") Long questionId, QuestionDTO questionDTO) {
        try {
            questionService.updateQuestion(questionDTO, questionId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/user/myinquiry";
    }

    // mypage 문의사항 삭제
    @DeleteMapping ("/inquiry/delete/{questionId}")
    public ResponseEntity<String> inquiryDelete(@PathVariable("questionId") Long questionId) {
        try {
            // 해당 faqId 삭제
            boolean qnaAnswerStatus = questionService.deleteQnaIfPending(questionId);
            if (qnaAnswerStatus) {
                return ResponseEntity.ok().body("삭제되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("삭제할 수 없습니다. 질문의 상태를 확인해주세요.");
            }

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("문의사항 삭제 중 문제가 발생했습니다.");
        }
    }


//    @DeleteMapping("/inquiry/attachedfile/delete/{questionId}")
//    public ResponseEntity<String> attachedFileDelete(@PathVariable("noticeId") Long noticeId) {
//        try {
//            questionService.deleteNoticeFile(noticeId);
//            return ResponseEntity.ok("첨부 파일 삭제 성공");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("첨부 파일 삭제 실패");
//        }
//    }

}