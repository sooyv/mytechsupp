package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import techsuppDev.techsupp.DTO.CommentDTO;
import techsuppDev.techsupp.DTO.FaqDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.NoticeFileEntity;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.CommentRepository;
import techsuppDev.techsupp.repository.NoticeFileRepository;
import techsuppDev.techsupp.service.CommentService;
import techsuppDev.techsupp.service.FaqService;
import techsuppDev.techsupp.service.NoticeService;
import techsuppDev.techsupp.service.QuestionService;

import javax.activation.CommandMap;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
    private final NoticeService noticeService;
    private final QuestionService questionService;

    private final FaqService faqService;

    private final CommentService commentService;

    private final NoticeFileRepository noticeFileRepository;
    private final CommentRepository commentRepository;

    @GetMapping("")
    public String serviceMain() {

        return "service/service-header";
    }

    @GetMapping("/faq")
    public String faqsaveForm() {

        return "service/faq";
    }

    @PostMapping("/faq")
    public String faqsave(@ModelAttribute FaqDTO faqDTO) throws IOException {

        faqService.save(faqDTO);
        return "service/faq-list";
    }

    @GetMapping("/faq-list")
    public String findfaqAll(Model model) {
        List<FaqDTO> faqDTOList = faqService.findAll();
        System.out.println(faqDTOList);
        model.addAttribute("faqList", faqDTOList);
        return "service/faq-list";
    }

    @GetMapping("/faq-list/{faqId}")
    public String faqfindById(@PathVariable Long faqId, Model model) {
        /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 faq_detail.html에 출력
         */
        faqService.updateHits(faqId);
        FaqDTO faqDTO = faqService.findById(faqId);
//        System.out.println(faqDTO.getfaqTitle());

        model.addAttribute("faq", faqDTO);
        return "service/faq-detail";
    }


    @GetMapping("/save")
    public String saveForm() {

        return "service/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute NoticeDTO noticeDTO) throws IOException {

        noticeService.save(noticeDTO);
        return "redirect:/notice/paging"; //templates/service/paging.html
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        System.out.println(noticeDTOList);
        model.addAttribute("noticeList", noticeDTOList);
        return "service/list";
    }

    @GetMapping("/{noticeId}")
    public String findById(@PathVariable Long noticeId, Model model) {
        /*
        해당 게시글의 조회수를 하나 올리고
        게시글 데이터를 가져와서 detail.html에 출력
         */
        noticeService.updateHits(noticeId);
        NoticeDTO noticeDTO = noticeService.findById(noticeId);
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
        System.out.println("&&&&&&&" + noticeDTO.getNoticeId());
        return "service/detail";
//        return "redirect:/notice/update" + noticeDTO.getNoticeId();

    }

    @GetMapping("/fileDownload/{noticeId}")
    @ResponseBody
    public void downloadFile(HttpServletResponse res, @PathVariable Long noticeId) throws UnsupportedEncodingException {

        //파일 조회
//        NoticeFileEntity noticeFile = noticeFileRepository.findById(noticeId).get();
        NoticeDTO noticeDTO = noticeService.findById(noticeId);

        //파일 경로
        Path savePath = Paths.get("C:/project file/techsupp/src/main/resources/static/file/service" + noticeDTO.getStoredFileName());
        //해당 경로에 파일이 없으면
        if (!savePath.toFile().exists()) {
            throw new RuntimeException("file not found");
        }

        //파일 헤더 설정
        setFileHeader(res, noticeDTO);

        //파일 복사
        fileCopy(res, savePath);
    }

    /**
     * 파일 header 설정
     *
     * @param res
     * @param noticeDTO
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, NoticeDTO noticeDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode((String) noticeDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }

    /**
     * 파일 복사
     *
     * @param res
     * @param savePath
     */
    private void fileCopy(HttpServletResponse res, Path savePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(savePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @GetMapping("/question")
    public String questionsaveForm() {

        return "service/question";
    }

    @PostMapping("/question")
    public String save(@ModelAttribute QuestionDTO questionDTO) throws IOException {
//        System.out.println("questionPass:" + questionDTO.getQuestionPass() );

        questionService.save(questionDTO);
        return "redirect:/notice/question-paging";
    }

    @GetMapping("/question-list")
    public String findQuestionAll(Model model) {
        List<QuestionDTO> questionDTOList = questionService.findAll();
        model.addAttribute("questionList", questionDTOList);
        return "service/question-list";
//        return "/question-check/{questionId}";
    }

    @GetMapping("/question-list/{questionId}")
    public String questionfindById(@PathVariable Long questionId, Model model) {

        QuestionDTO questionDTO = questionService.findById(questionId);
        System.out.println(questionDTO.getQuestionTitle());
        List<CommentDTO> commentDTOList = commentService.findAll(questionId);

        model.addAttribute("question", questionDTO);
        model.addAttribute("commentList", commentDTOList);

//        return "service/question-check";
        return "service/question-detail";
    }

    @GetMapping("/question-check/{questionId}")
    public String questionCheck(@PathVariable Long questionId, Model model) {
        model.addAttribute("questionId", questionId);
        return "service/question-check";
//        return "service/question-detail";
    }

    @GetMapping("/question-check/getPass")
    @ResponseBody
    public boolean getPass(@RequestParam("questionId") Long questionId,
                           @RequestParam("questionPass") String questionPass, Model model) throws Exception {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(questionId);
        QuestionDTO result = questionService.findById(questionId);

        boolean flag = false;

        if (result.getQuestionPass().equals(questionPass)) {
            flag = true;
        }
        return flag;
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


    @GetMapping("/question-list/fileDownload/{questionId}")
    @ResponseBody
    public void questiondownloadFile(HttpServletResponse res, @PathVariable Long questionId) throws UnsupportedEncodingException {

        //파일 조회
//        NoticeFileEntity noticeFile = noticeFileRepository.findById(noticeId).get();
        QuestionDTO questionDTO = questionService.findById(questionId);

        //파일 경로
        Path savePath = Paths.get("C:/project file/techsupp/src/main/resources/static/file/service" + questionDTO.getStoredFileName());
        //해당 경로에 파일이 없으면
        if (!savePath.toFile().exists()) {
            throw new RuntimeException("file not found");
        }

        //파일 헤더 설정
        setFileHeader(res, questionDTO);

        //파일 복사
        fileCopy(res, savePath);
    }

    /**
     * 파일 header 설정
     *
     * @param res
     * @param questionDTO
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, QuestionDTO questionDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode((String) questionDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }

    /**
     * 파일 복사
     *
     * @param res
     * @param savePath
     */
    private void questionfileCopy(HttpServletResponse res, Path savePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(savePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    // /notice/paging?page=1
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
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

        return "service/paging";
    }

    @GetMapping("/question-paging")
    public String questionpaging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<QuestionDTO> questionList = questionService.paging(pageable);

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

        return "service/question-paging";
    }


}
