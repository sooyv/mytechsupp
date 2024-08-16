package techsuppDev.techsupp.DTO;

import lombok.*;
import techsuppDev.techsupp.domain.QuestionAnswer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Getter @Setter
@AllArgsConstructor
public class QuestionAnswerDTO {
    private Long answerId;
    private String answerWriter;
    private String answerContent;
    private LocalDateTime createdAtA;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static QuestionAnswerDTO fromEntity(QuestionAnswer questionAnswer) {
        if (questionAnswer == null) {
            return null;
        }
        return new QuestionAnswerDTO(
                questionAnswer.getAnswerId(),
                questionAnswer.getAnswerWriter(),
                questionAnswer.getAnswerContent(),
                questionAnswer.getCreatedAtA()
        );
    }

    // 글 생성일자(년, 월, 일) 출력
    public String getFormattedCreatedAtA() {
        return createdAtA.format(DATE_FORMATTER);
    }
}

