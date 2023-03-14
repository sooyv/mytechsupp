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
    name = "feedbackImageMapping",
    columns = {
        @ColumnResult(name = "img_id", type = Long.class),
        @ColumnResult(name = "img_name", type = String.class),
        @ColumnResult(name = "origin_img_name", type = String.class),
        @ColumnResult(name = "img_url", type = String.class),
        @ColumnResult(name = "rep_img", type = Long.class),
        @ColumnResult(name = "id", type = String.class),
        @ColumnResult(name = "feedback_id", type = Long.class)
    }
)
public class FeedbackImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;
    private String imgName;
    private String originImgName;
    private String imgUrl;
    private String repImg;
    private Long id;
    private Long FeedbackId;
}
