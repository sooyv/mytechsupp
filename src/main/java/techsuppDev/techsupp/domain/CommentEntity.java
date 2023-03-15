package techsuppDev.techsupp.domain;


import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.CommentDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_question_Id")
    private QuestionEntity questionEntity;


    public static CommentEntity toSaveEntity(CommentDTO commentDTO, QuestionEntity questionEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setQuestionEntity(questionEntity);
        return commentEntity;
    }
}
