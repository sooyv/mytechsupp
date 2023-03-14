package techsuppDev.techsupp.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SqlResultSetMapping(
    name = "feedbackProductMapping",
    columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "moddate", type = LocalDateTime.class),
        @ColumnResult(name = "regdate", type = LocalDateTime.class),
        @ColumnResult(name = "click_count", type = int.class),
        @ColumnResult(name = "information", type = String.class),
        @ColumnResult(name = "invest_price", type = Integer.class),
        @ColumnResult(name = "period", type = LocalDate.class),
        @ColumnResult(name = "product_name", type = String.class),
        @ColumnResult(name = "product_status", type = String.class),
        @ColumnResult(name = "seq_id", type = Long.class),
        @ColumnResult(name = "total_price", type = Integer.class),
        @ColumnResult(name = "img_url", type = String.class)
    })
public class FeedbackProductListForm {
    @Id
    private Long id;
    private String moddate;
    private String  regdate;
    private int clickCount;
    private String information;
    private int investPrice;
    private String period;
    private String productName;
    private String productStatus;
    private Long seqId;
    private int totalPrice;
    private String imgUrl;

}
