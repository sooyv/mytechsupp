package techsuppDev.techsupp.domain;


import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.QuestionDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question_table")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long questionId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
//    private Long userId;

    @Column
    private String questionTitle;

    @Column(length = 500)
    private String questionDetail;

    @Column
    private String questionCategory;

//    @Column
//    private QuestionStatus questionStatus;

    @Column
    private String questionAnswer;

    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionDetail(questionDTO.getQuestionDetail());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionAnswer(questionDTO.getQuestionAnswer());

        return questionEntity;

    }
}
