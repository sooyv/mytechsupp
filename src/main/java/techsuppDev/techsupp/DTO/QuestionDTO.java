package techsuppDev.techsupp.DTO;

import lombok.*;
import techsuppDev.techsupp.domain.QuestionEntity;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionWriter;
    private String questionCategory;
    private String questionTitle;
    private String questionContents;
//    private Date questionDate;
//    private int questionStatus;
    private String questionAnswer;

    public static QuestionDTO toquestionDTO(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setQuestionId(questionEntity.getQuestionId());
        questionDTO.setQuestionWriter(questionEntity.getQuestionWriter());
        questionDTO.setQuestionCategory(questionEntity.getQuestionCategory());
        questionDTO.setQuestionTitle(questionEntity.getQuestionTitle());
        questionDTO.setQuestionContents(questionEntity.getQuestionContents());
        questionDTO.setQuestionAnswer(questionEntity.getQuestionAnswer());
        return questionDTO;
    }



}
