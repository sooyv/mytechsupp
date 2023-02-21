package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
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
    Long paymentId;
    Long productId;
    String streetAddr;
    String detailAddr;
    String zipCode;

    int paymentPrice;
    Date paymentDate;

    String paymentMethod;
}
