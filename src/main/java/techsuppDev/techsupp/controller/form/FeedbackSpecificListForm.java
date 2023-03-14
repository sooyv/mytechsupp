package techsuppDev.techsupp.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SqlResultSetMapping(
    name = "feedbackSpecificListMapping",
    columns = {
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "user_id", type = Long.class),
        @ColumnResult(name = "img_url", type = String.class),
        @ColumnResult(name = "feedback_status", type = String.class),
        @ColumnResult(name = "feedback_text", type = String.class),
        @ColumnResult(name = "score", type = Integer.class),
    })
public class FeedbackSpecificListForm {
    private Long productId;
    private Long userId;
    private String imgUrl;
    private String feedbackStatus;
    private String feedbackText;
    private int score;
}
