package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SqlResultSetMapping(
    name = "feedbackMapping",
    columns = {
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "user_id", type = Long.class),
        @ColumnResult(name = "feedback_text", type = String.class),
        @ColumnResult(name = "score", type = Integer.class),
        @ColumnResult(name = "feedback_status", type = String.class)
    }
)
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long userId;
    private String feedbackText;
    private int score;
//    1 ~ 10
    private FeedbackStatus feedbackStatus;

}
