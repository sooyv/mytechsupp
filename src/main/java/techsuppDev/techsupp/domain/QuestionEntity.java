package techsuppDev.techsupp.domain;


import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.QuestionDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String questionTitle;
    @Column(length = 500)
    private String questionContents;

    @Column(name = "created_at_q", nullable = false, updatable = false)
    private LocalDateTime createdAtQ;

    @Column(name = "updated_at_q")
    private LocalDateTime updatedAtQ;

    @Enumerated(EnumType.STRING)
    @Column
    private QuestionCategory questionCategory;

    @Enumerated(EnumType.STRING)
    @Column
    private QuestionStatus questionStatus = QuestionStatus.PENDING; // 기본값 설정

    @Column(name = "is_private", nullable = false)
    private Boolean is_private;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<QuestionFileEntity> questionFileEntityList = new ArrayList<>();

//    @OneToMany(mappedBy = "questionEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<CommentEntity> commentEntityList = new ArrayList<>();

    @OneToOne(mappedBy = "questionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private QuestionAnswer questionAnswer;

    // 파일 첨부 없이 등록
    public static QuestionEntity toSaveEntity(QuestionDTO questionDTO){
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionStatus(QuestionStatus.PENDING);
        questionEntity.setIs_private(questionDTO.getIs_private());
        questionEntity.setFileAttached(0);

        return questionEntity;
    }

    public static QuestionEntity toUpdateEntity(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionId(questionDTO.getQuestionId());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());

        return questionEntity;
    }

    public static QuestionEntity toSaveFileEntity(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setQuestionTitle(questionDTO.getQuestionTitle());
        questionEntity.setQuestionContents(questionDTO.getQuestionContents());
        questionEntity.setQuestionCategory(questionDTO.getQuestionCategory());
        questionEntity.setQuestionStatus(questionDTO.getQuestionStatus());
        questionEntity.setFileAttached(1);

        return questionEntity;
    }


    // 문의 카테고리 - Getter,Setter
    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    // 문의 답변 상태 카테고리 - Getter,Setter
    public QuestionStatus getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }
}
