package techsuppDev.techsupp.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SqlResultSetMapping(
        name = "AdminPaymentForm",
        columns = {
                @ColumnResult(name = "payment_id", type = Long.class),
                @ColumnResult(name = "product_name", type = String.class),
                @ColumnResult(name = "user_email", type = String.class),
                @ColumnResult(name = "payment_method", type = String.class),
                @ColumnResult(name = "payment_price", type = int.class),
                @ColumnResult(name = "payment_date", type = String.class),
                @ColumnResult(name = "paylog_status", type = String.class)
        }
)
public class AdminPaymentForm {

    @Id
    private Long paymentId;
    private String productName;
    private String userEmail;
    private String paymentMethod;
    private int paymentPrice;
    private String paymentDate;
    private String paylogStatus;
}
