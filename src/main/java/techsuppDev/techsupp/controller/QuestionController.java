//package techsuppDev.techsupp.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import techsuppDev.techsupp.DTO.NoticeDTO;
//import techsuppDev.techsupp.DTO.QuestionDTO;
//import techsuppDev.techsupp.service.NoticeService;
//import techsuppDev.techsupp.service.QuestionService;
//
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/notice")
//public class QuestionController {
//
//    private final QuestionService questionService;
//
//    @GetMapping("/service-main")
//    public String serviceMain() {
//
//        return "service/service-main";
//    }
//
//    @GetMapping("/question")
//    public String saveForm() {
//
//        return "service/question";
//    }
//
//    @PostMapping("/question")
//    public String save(@ModelAttribute QuestionDTO questionDTO) {
//
//        questionService.save(questionDTO);
//        return "service-main";
//    }
//}
