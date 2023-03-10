package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SqlResultSetMapping(
    name = "PayHistoryForm",
    columns = {
        @ColumnResult(name = "payment_id", type = Long.class),
        @ColumnResult(name = "paylog_id", type = Long.class),
        @ColumnResult(name = "user_email", type = String.class),
        @ColumnResult(name = "paylog_status", type = String.class),
        @ColumnResult(name = "detail_addr", type = String.class),
        @ColumnResult(name = "payment_date", type = String.class),
        @ColumnResult(name = "payment_method", type = String.class),
        @ColumnResult(name = "payment_price", type = int.class),
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "street_addr", type = String.class),
        @ColumnResult(name = "zip_code", type = String.class)

    }
)
public class PayHistoryForm {
    @Id
    private Long paymentId;
    private Long paylogId;
    private String userEmail;
    private String paylogStatus;
    private String detailAddr;
    private String paymentDate;
    private String paymentMethod;
    private int paymentPrice;
    private Long productId;
    private String streetAddr;
    private String zipCode;

}
