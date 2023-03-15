package techsuppDev.techsupp.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import techsuppDev.techsupp.domain.CommentEntity;

@Getter
@Setter
@ToString

public class CommentDTO {

    private Long commentId;
    private String commentWriter;
    private String commentContents;
    private Long questionId;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity, Long questionId) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentId(commentEntity.getQuestionId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setQuestionId(questionId);
        return commentDTO;
    }
}
