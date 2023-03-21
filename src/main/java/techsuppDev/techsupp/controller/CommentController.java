package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import techsuppDev.techsupp.DTO.CommentDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.service.CommentService;
import techsuppDev.techsupp.service.QuestionService;

import java.util.List;


// 문의글 댓글
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
//    private final QuestionService questionService;

    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CommentDTO commentDTO) {
        System.out.println("commentDTO = " + commentDTO);
        Long saveResult = commentService.save(commentDTO);
        if (saveResult != null) {
            // 작성 성공하면 댓글목록을 가져와서 리턴
            // 댓글목록: 해당 게시글의 댓글 전체
            // 댓글이 달리면 AnswerCompleted

//            QuestionDTO questionDTO = questionService.findById(commentDTO.getQuestionId());
//
//            questionService.updateStatus(questionDTO);


//            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getQuestionId());
//            System.out.println("test:" + commentDTOList);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            // 댓글이 달리지 않으면  AnswerWaiting

//            QuestionStatus questionStatus = QuestionStatus.AnswerWaiting;

            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
//        return "요청성공";
    }
}
