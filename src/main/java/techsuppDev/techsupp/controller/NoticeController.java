package techsuppDev.techsupp.controller;

import groovyjarjarpicocli.CommandLine;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.service.NoticeService;
import techsuppDev.techsupp.service.QuestionService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final QuestionService questionService;

    private final PasswordEncoder passwordEncoder;

    @GetMapping("")
    public String serviceMain() {

        return "service/service-main";
    }

    @GetMapping("/save")
    public String saveForm() {

        return "service/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) throws IOException {

        noticeService.save(noticeDTO);
        return "service/service-main";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        System.out.println(noticeDTOList);
        model.addAttribute("noticeList",noticeDTOList);
        return "service/list";
    }

    @GetMapping("/{noticeId}")
    public String findById(@PathVariable Long noticeId, Model model) {
        /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 detail.html에 출력
         */
        noticeService.updateHits(noticeId);
        NoticeDTO noticeDTO =noticeService.findById(noticeId);
        System.out.println(noticeDTO.getNoticeTitle());

        model.addAttribute("notice", noticeDTO);
        return "service/detail";
    }

    @GetMapping("update/{noticeId}")
    public String updateForm(@PathVariable Long noticeId, Model model) {
        NoticeDTO noticeDTO = noticeService.findById(noticeId);
        model.addAttribute("noticeUpdate", noticeDTO);
        return "service/update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
        return "service/detail";
        // return "redirect:/notice/" + noticeDTO.getNoticeId();
    }

    @GetMapping("/question")
    public String questionsaveForm() {

        return "service/question";
    }

    @PostMapping("/question")
    public String save(@ModelAttribute QuestionDTO questionDTO) throws IOException {

        questionService.save(questionDTO);
        return "service/service-main";
    }

    @GetMapping("/question-list")
    public String findQuestionAll(Model model) {
        List<QuestionDTO> questionDTOList = questionService.findAll();
        model.addAttribute("questionList", questionDTOList);
        return "service/question-list";
    }

    //  문의글 비밀번호 확인
//    @GetMapping("/question-list/{questionId}")
//    public String checkView() {
//        return "service/question-check";
//    }
//
//    //    비밀번호 확인 체크
//
//    @PostMapping("/question-check")
//    @ResponseBody
//    public boolean checkPass(@RequestParam String questionPass, Model model) throws Exception {
//    boolean result = false;  //
//
//
//    //  작성자 조회
//
//    QuestionDTO questionDTO = (QuestionDTO) model.getAttribute("questionId"); // 기존 문의정보 db 확인
//
//    Long questionId = "{questionId}"; // 데이터베이스 JPA를 통해서 조회
//    questionService.checkPass(questionId);
//
//    if (passwordEncoder.matches(questionPass, questionService.checkPass(questionId))) {
//        result = true;
//    } else {
//        result = false;
//    }   //현재 비밀번호
//
//    return result;
//    }


    @GetMapping("/question-list/{questionId}")
    public String questionfindById(@PathVariable Long questionId, Model model) {

        QuestionDTO questionDTO = questionService.findById(questionId);
        System.out.println(questionDTO.getQuestionTitle());

        model.addAttribute("question", questionDTO);
        return "service/question-detail";
    }

    @GetMapping("/question-update/{questionId}")
    public String questionUpdateForm(@PathVariable Long questionId, Model model) {
        QuestionDTO questionDTO = questionService.findById(questionId);
        model.addAttribute("questionUpdate", questionDTO);
        return "service/question-update";
    }

    @PostMapping("/question-update")
    public String questionupdate(@ModelAttribute QuestionDTO questionDTO, Model model) {
        QuestionDTO question = questionService.update(questionDTO);
        model.addAttribute("question", question);
        return "/service/question-detail";
    }


}
