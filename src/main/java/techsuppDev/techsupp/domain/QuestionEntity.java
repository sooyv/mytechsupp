package techsuppDev.techsupp.domain;


import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.QuestionDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(length = 255)
    private String questionPass;

    @Column
    private String questionCategory;

    @Column
    private String questionTitle;

    @Column(length = 500)
    private String questionContents;

//    @Enumerated(EnumType.STRING)
//    @Column
//    private QuestionStatus questionStatus;

//    @Column
//    private int questionStatus;

//    @Column
//    private String questionAnswer;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionFileEntity> questionFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionWriter(questionDTO.getQuestionWriter());
        questionEntity.setQuestionPass(questionDTO.getQuestionPass());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
//        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
//        questionEntity.setQuestionAnswer(questionDTO.getQuestionAnswer());
        questionEntity.setFileAttached(0);

        return questionEntity;

    }

    public static QuestionEntity toUpdateEntity(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionId(questionDTO.getQuestionId());
        questionEntity.setQuestionWriter(questionDTO.getQuestionWriter());
        questionEntity.setQuestionPass(questionDTO.getQuestionPass());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
//        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
//        questionEntity.setQuestionAnswer(questionDTO.getQuestionAnswer());

        return questionEntity;
    }

    public static QuestionEntity toSaveFileEntity(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionWriter(questionDTO.getQuestionWriter());
        questionEntity.setQuestionPass(questionDTO.getQuestionPass());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
//        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
//        questionEntity.setQuestionAnswer(questionDTO.getQuestionAnswer());
        questionEntity.setFileAttached(1);
        return questionEntity;
    }

//    public static QuestionEntity updateStatus(QuestionStatus questionStatus) {
//        QuestionEntity questionEntity = new QuestionEntity();
//        questionEntity.setQuestionStatus(QuestionStatus.AnswerCompleted);
//        return questionEntity;
//    }





}
