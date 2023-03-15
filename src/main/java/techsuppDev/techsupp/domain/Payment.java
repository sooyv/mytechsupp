package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SqlResultSetMapping(
    name = "PaymentMapping",
    columns = {
        @ColumnResult(name = "payment_id", type = Long.class),
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "street_addr", type = String.class),
        @ColumnResult(name = "detail_addr", type = String.class),
        @ColumnResult(name = "zip_code", type = String.class),
        @ColumnResult(name = "payment_price", type = int.class),
        @ColumnResult(name = "payment_date", type = LocalDateTime.class),
        @ColumnResult(name = "payment_method", type = String.class)
    }
)
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private Long productId;
    private String streetAddr;
    private String detailAddr;
    private String zipCode;
    private int paymentPrice;
    private LocalDateTime paymentDate;
    private String paymentMethod;
}
