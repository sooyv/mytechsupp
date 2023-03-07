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
