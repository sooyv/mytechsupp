package techsuppDev.techsupp.DTO;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Paylog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paylogId;
    private Long userId;
    private int paymentId;
    private int paylogStatus;
}
