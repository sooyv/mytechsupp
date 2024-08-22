package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import techsuppDev.techsupp.DTO.FaqDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.service.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cs")
public class NoticeController {
    private final NoticeService noticeService;
    private final QuestionService questionService;
    private final FaqService faqService;
    private final AttachmentFileService attachmentFileService;

    @Value("${noticeServicePath}")
    String noticeServicePath;


    /**
     * 공지사항
     */

    // 공지사항 리스트
    @GetMapping("/notice-list")
    public String noticeList(@PageableDefault(page = 1) Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<NoticeDTO> noticeList = noticeService.paging(pageable);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < noticeList.getTotalPages()) ? startPage + blockLimit - 1 : noticeList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "service/notice-list";
    }

    // 공지사항 상세 확인 페이지 - FOR USER
    @GetMapping("/notice/{noticeId}")
    public String noticeDetailPage(@PathVariable Long noticeId, Model model) {
        /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 detail.html에 출력
         */
        noticeService.updateHits(noticeId);
        NoticeDTO noticeDTO = noticeService.findById(noticeId);

        model.addAttribute("notice", noticeDTO);
        return "service/notice-detail";
    }



    /**
     * 자주 묻는 질문
     */

    // 자주 묻는 질문 리스트
    @GetMapping("/faq-list")
    public String faqList(Model model) {
        List<FaqDTO> faqDTOList = faqService.findAll();
        System.out.println(faqDTOList);
        model.addAttribute("faqList", faqDTOList);
        return "service/faq-list";
    }

    // 자주 묻는 질문 detail
    @GetMapping("/faq/{faqId}")
    public String faqDetailPage(@PathVariable Long faqId, Model model) {
        /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 faq_detail.html에 출력
         */
        faqService.updateHits(faqId);
        FaqDTO faqDTO = faqService.findById(faqId);

        model.addAttribute("faq", faqDTO);
        return "service/faq-detail";
    }


    // 공지사항 파일 다운로드
    @GetMapping("/notice/download/file/{noticeId}")
    @ResponseBody
    public void noticeDownloadFile(HttpServletResponse res, @PathVariable Long noticeId) throws UnsupportedEncodingException, FileNotFoundException {
        String csType = "notice";

        // 1. 파일 조회
        NoticeDTO noticeDTO = noticeService.findById(noticeId);

        // 2. 파일 헤더 설정
        attachmentFileService.setFileHeader(res, noticeDTO);

        // 3. 파일 경로 확인, 파일 복사
        attachmentFileService.filePath(res, noticeDTO, csType);
    }


    /**
     * 문의 사항
     */
    @GetMapping("/question-list")
    public String questionList(@PageableDefault(page = 1) Pageable pageable, Principal principal, Model model) {
        String currentUserEmail = (principal != null) ? principal.getName() : null;
        boolean isAuthenticated = currentUserEmail != null; // 로그인 여부

        pageable.getPageNumber();
        Page<QuestionDTO> questionList = questionService.paging(pageable, currentUserEmail, isAuthenticated);

        int blockLimit = 5;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1; // 1 4 7 10 ~~
        int endPage = ((startPage + blockLimit - 1) < questionList.getTotalPages()) ? startPage + blockLimit - 1 : questionList.getTotalPages();

        // page 갯수 20개
        // 현재 사용자가 3페이지
        // 1 2 3
        // 현재 사용자가 7페이지
        // 7 8 9
        // 보여지는 페이지 갯수 3개
        // 총 페이지 갯수 8개

        model.addAttribute("questionList", questionList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentUserEmail", currentUserEmail); // 현재 로그인한 사용자 이메일 추가
        model.addAttribute("isAuthenticated", isAuthenticated); // 로그인 여부 추가

        return "service/question-paging";
    }

    // 문의 작성
    @GetMapping("/question")
    public String qnaResister() {
        return "service/question";
    }

    // 문의 작성 post
    @PostMapping("/question")
    public String qnaResisterPost(@ModelAttribute QuestionDTO questionDTO) throws IOException {
        System.out.println("controller is_private : "+ questionDTO.isSecretPost());
        questionService.questionResister(questionDTO);
        return "redirect:/cs/question-list";
    }

    // 문의사항 상세 확인 페이지 - FOR USER
    @GetMapping("/qna/{questionId}")
    public String qnaDetailPage(@PathVariable Long questionId, Model model) {
        QuestionDTO question = questionService.findByIdWithAnswer(questionId);

        System.out.println("파일 확인 1 : "+ question.getOriginalFileName());

        model.addAttribute("question", question);

        return "service/qna-detail";
    }


//    @GetMapping("/question-list")
//    public String findQuestionAll(Model model) {
//        List<QuestionDTO> questionDTOList = questionService.findAll();
//        model.addAttribute("questionList", questionDTOList);
//        return "service/question-list";
////        return "/question-check/{questionId}";
//    }

//    @GetMapping("/question-list/{questionId}")
//    public String questionfindById(@PathVariable Long questionId, Model model) {
//
//        QuestionDTO questionDTO = questionService.findById(questionId);
//        System.out.println(questionDTO.getQuestionTitle());
//        List<CommentDTO> commentDTOList = commentService.findAll(questionId);
//
//        model.addAttribute("question", questionDTO);
//        model.addAttribute("commentList", commentDTOList);
//
////        return "service/question-check";
//        return "service/question-detail";
//    }


//    @GetMapping("/question-check/{questionId}")
//    public String questionCheck(@PathVariable Long questionId, Model model) {
//        model.addAttribute("questionId", questionId);
//        return "service/question-check";
////        return "service/question-detail";
//    }



    @GetMapping("/question-update/{questionId}")
    public String questionUpdate(@PathVariable Long questionId, Model model) {
        QuestionDTO questionDTO = questionService.findById(questionId);
        model.addAttribute("questionUpdate", questionDTO);
        return "service/question-update";
    }

    @PostMapping("/question-update")
    public String questionUpdatePost(@ModelAttribute QuestionDTO questionDTO, Model model) {
        QuestionDTO question = questionService.update(questionDTO);
        model.addAttribute("question", question);
        return "/service/question-detail";
    }


    // 문의사항 파일 다운로드
    @GetMapping("/qna/download/file/{questionId}")
    @ResponseBody
    public void questionDownloadFile(HttpServletResponse res, @PathVariable Long questionId) throws UnsupportedEncodingException, FileNotFoundException {
        String csType = "question";

        // 1. 파일 조회
        QuestionDTO questionDTO = questionService.findById(questionId);

        // 2. 파일 헤더 설정
        attachmentFileService.setFileHeader(res, questionDTO);

        // 3. 파일 경로 확인, 파일 복사
        attachmentFileService.filePath(res, questionDTO, csType);
    }


}
