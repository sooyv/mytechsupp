package techsuppDev.techsupp.domain;


import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.QuestionDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long questionId;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
//    private Long userId;
    @Column(length = 20, nullable = false)
    private String questionWriter;

    @Column
    private String questionCategory;

    @Column
    private String questionTitle;

    @Column(length = 500)
    private String questionContents;

//    @Column
//    private QuestionStatus questionStatus;

    @Column
    private String questionAnswer;

    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionWriter(questionDTO.getQuestionWriter());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionAnswer(questionDTO.getQuestionAnswer());

        return questionEntity;

    }
}
