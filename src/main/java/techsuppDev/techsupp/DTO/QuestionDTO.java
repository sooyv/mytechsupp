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
public class QuestionDTO extends ServiceDTO {
    private QuestionStatus questionStatus;
    private QuestionCategory questionCategory;
    private MultipartFile questionFile;
    private boolean secretPost;
    private QuestionAnswerDTO questionAnswer; // 답변 정보를 포함할 필드

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public QuestionDTO(Long questionId, QuestionStatus questionStatus, String questionTitle,
                       String userName, String userEmail, LocalDateTime createdAtQ, boolean secretPost) {
        setPostId(questionId); // 상속받은 메서드를 통해 설정
        setUserEmail(userEmail);
        setUserName(userName);
        setPostTitle(questionTitle);
        setCreateAt(createdAtQ);
        setPostContents(null); // 별도로 설정
        setFileAttached(0); // 기본값
        this.questionStatus = questionStatus;
        this.secretPost = secretPost;
    }

    public static QuestionDTO toQuestionDTO(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setPostId(questionEntity.getQuestionId());
        questionDTO.setUserName(questionEntity.getUser().getUserName());
        questionDTO.setPostTitle(questionEntity.getQuestionTitle());
        questionDTO.setPostContents(questionEntity.getQuestionContents());
        questionDTO.setCreateAt(questionEntity.getCreatedAtQ());
        questionDTO.setUpdateAt(questionEntity.getUpdatedAtQ());
        questionDTO.setFileAttached(questionEntity.getFileAttached());
        questionDTO.setQuestionStatus(questionEntity.getQuestionStatus());
        questionDTO.setQuestionCategory(questionEntity.getQuestionCategory());
        questionDTO.setSecretPost(questionEntity.isSecretPost());

        if (questionEntity.getQuestionFileEntity() != null) {
            questionDTO.setOriginalFileName(questionEntity.getQuestionFileEntity().getOriginalFileName());
            questionDTO.setStoredFileName(questionEntity.getQuestionFileEntity().getStoredFileName());
        }

        return questionDTO;
    }


    // 글 생성일자(년, 월, 일) 출력
    public String getFormattedCreatedAtQ() {
        return getCreateAt().format(DATE_FORMATTER);
    }
}
