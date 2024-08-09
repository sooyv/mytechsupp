package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "answer_content")
    private String answerContent;

    @OneToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity questionEntity;

}
