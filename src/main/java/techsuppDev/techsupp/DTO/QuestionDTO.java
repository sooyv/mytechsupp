package techsuppDev.techsupp.DTO;

import lombok.*;

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
    private Long userId;
    private int SeqId;
    private String questionTitle;
    private String questionDetail;
    private String questionCategory;
    private Date questionDate;
    private int questionStatus;
    private String questionAnswer;



}
