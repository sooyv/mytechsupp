package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "question_file")
public class QuestionFileEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long questionId;
//
//    @Column
//    private String originalFileName;
//
//    @Column
//    private String storedFileName;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "question_question_id")
//    private QuestionEntity questionEntity;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionFileId;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity questionEntity;

    public static QuestionFileEntity toQuestionFileEntity(QuestionEntity questionEntity, String originalFileName, String storedFileName){
        QuestionFileEntity questionFileEntity = new QuestionFileEntity();
        questionFileEntity.setOriginalFileName(originalFileName);
        questionFileEntity.setStoredFileName(storedFileName);
        questionFileEntity.setQuestionEntity(questionEntity);
        return questionFileEntity;
    }
}
