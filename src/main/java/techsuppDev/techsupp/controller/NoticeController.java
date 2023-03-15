package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.NoticeFileEntity;
import techsuppDev.techsupp.domain.User;
import techsuppDev.techsupp.repository.NoticeFileRepository;
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

    private final PasswordEncoder passwordEncoder;
    private final NoticeFileRepository noticeFileRepository;

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
        return "service/detail";
        // return "redirect:/notice/" + noticeDTO.getNoticeId();
    }

    @GetMapping("/fileDownload/{noticeId}")
    @ResponseBody
    public void downloadFile(HttpServletResponse res, @PathVariable Long noticeId) throws UnsupportedEncodingException {

        //파일 조회
//        NoticeFileEntity noticeFile = noticeFileRepository.findById(noticeId).get();
        NoticeDTO noticeDTO = noticeService.findById(noticeId);

        //파일 경로
        Path savePath = Paths.get("C:/springboot_img/" + noticeDTO.getStoredFileName());
        //해당 경로에 파일이 없으면
        if(!savePath.toFile().exists()) {
            throw new RuntimeException("file not found");
        }

        //파일 헤더 설정
        setFileHeader(res, noticeDTO);

        //파일 복사
        fileCopy(res, savePath);
    }

    /**
     * 파일 header 설정
     * @param res
     * @param noticeDTO
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, NoticeDTO noticeDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" +  URLEncoder.encode((String) noticeDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }

    /**
     * 파일 복사
     * @param res
     * @param savePath
     */
    private void fileCopy(HttpServletResponse res, Path savePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(savePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                fis.close();
            }
            catch (Exception e) {
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

        questionService.save(questionDTO);
        return "service/service-main";
    }

    @GetMapping("/question-list")
    public String findQuestionAll(Model model) {
        List<QuestionDTO> questionDTOList = questionService.findAll();
        model.addAttribute("questionList", questionDTOList);
        return "service/question-list";
    }

//    @GetMapping("/question-check")
//    @ResponseBody
//    private QuestionDTO getPassword(@RequestParam("questionId") Long questionId,
//                                    @RequestParam("questionPass") String questionPass, Model model) throws Exception {
//        QuestionDTO questionDTO = new QuestionDTO();
//        questionDTO.setQuestionId(questionId);
//        QuestionDTO result = questionService.findAll().get(questionPass);
//        return result;
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


    @GetMapping("/question-list/fileDownload/{questionId}")
    @ResponseBody
    public void questiondownloadFile(HttpServletResponse res, @PathVariable Long questionId) throws UnsupportedEncodingException {

        //파일 조회
//        NoticeFileEntity noticeFile = noticeFileRepository.findById(noticeId).get();
        QuestionDTO questionDTO = questionService.findById(questionId);

        //파일 경로
        Path savePath = Paths.get("C:/springboot_img/" + questionDTO.getStoredFileName());
        //해당 경로에 파일이 없으면
        if(!savePath.toFile().exists()) {
            throw new RuntimeException("file not found");
        }

        //파일 헤더 설정
        setFileHeader(res, questionDTO);

        //파일 복사
        fileCopy(res, savePath);
    }

    /**
     * 파일 header 설정
     * @param res
     * @param questionDTO
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, QuestionDTO questionDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" +  URLEncoder.encode((String) questionDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }

    /**
     * 파일 복사
     * @param res
     * @param savePath
     */
    private void questionfileCopy(HttpServletResponse res, Path savePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(savePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                fis.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


}
