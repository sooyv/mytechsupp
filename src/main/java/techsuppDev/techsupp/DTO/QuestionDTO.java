package techsuppDev.techsupp.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.domain.QuestionCategory;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.domain.QuestionStatus;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long questionId;
    private String userEmail;
    private String userName;
    private String questionTitle;
    private String questionContents;
    private LocalDateTime createdAtQ;
    private LocalDateTime updatedAtQ;
    private QuestionStatus questionStatus;
    private QuestionCategory questionCategory;
    private MultipartFile questionFile;
    private boolean secretPost;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached;
    private QuestionAnswerDTO questionAnswer; // 답변 정보를 포함할 필드

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public QuestionDTO(Long questionId, QuestionStatus questionStatus, String questionTitle,
                       String userName, String userEmail, LocalDateTime createdAtQ, boolean secretPost) {
        this.questionId = questionId;
        this.questionStatus = questionStatus;
        this.questionTitle = questionTitle;
        this.userName = userName;
        this.userEmail = userEmail;
        this.createdAtQ = createdAtQ;
        this.secretPost = secretPost;
    }

    public static QuestionDTO toQuestionDTO(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(questionEntity.getQuestionId());
        questionDTO.setUserName(questionEntity.getUser().getUserName());
        questionDTO.setQuestionCategory(questionEntity.getQuestionCategory());
        questionDTO.setQuestionTitle(questionEntity.getQuestionTitle());
        questionDTO.setQuestionContents(questionEntity.getQuestionContents());
        questionDTO.setQuestionStatus(questionEntity.getQuestionStatus());
        questionDTO.setCreatedAtQ(questionEntity.getCreatedAtQ());

        if (questionEntity.getFileAttached() == 0) {
            questionDTO.setFileAttached(questionEntity.getFileAttached()); // 0
        } else {
            questionDTO.setFileAttached(questionEntity.getFileAttached()); // 1
            // 파일 이름을 가져가야 함.
            questionDTO.setOriginalFileName(questionEntity.getQuestionFileEntityList().get(0).getOriginalFileName());
            questionDTO.setStoredFileName(questionEntity.getQuestionFileEntityList().get(0).getStoredFileName());
        }

        return questionDTO;
    }

    // 글 생성일자(년, 월, 일) 출력
    public String getFormattedCreatedAtQ() {
        return createdAtQ.format(DATE_FORMATTER);
    }
}
