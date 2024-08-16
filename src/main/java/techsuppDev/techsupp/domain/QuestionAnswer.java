package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "question_answer")
public class QuestionAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "answer_writer")
    private String answerWriter;

    @Column(name = "answer_content")
    private String answerContent;

    @CreationTimestamp
    @Column(name = "created_at_a", updatable = false)
    private LocalDateTime createdAtA;

    @OneToOne
    @JoinColumn(name = "questionId")
    private QuestionEntity questionEntity;

}
