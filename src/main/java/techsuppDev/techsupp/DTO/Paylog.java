package techsuppDev.techsupp.DTO;

import lombok.*;
import techsuppDev.techsupp.domain.PaylogStatus;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SqlResultSetMapping(
    name = "PaylogMapping",
    columns = {
        @ColumnResult(name = "paylog_id", type = Long.class),
        @ColumnResult(name = "user_Email", type = String.class),
        @ColumnResult(name = "payment_id", type = Long.class),
        @ColumnResult(name = "paylog_status", type = String.class)
    })
@Table(name = "paylog")
public class Paylog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paylogId;
    private String userEmail;
    private Long paymentId;
    private PaylogStatus paylogStatus;
}
