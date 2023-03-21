package techsuppDev.techsupp.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PaymentForm {
    private Long paymentId;
    private Long productId;
    private String streetAddr;
    private String detailAddr;
    private String zipCode;
    private int paymentPrice;
    private String paymentDate;

    private String paymentMethod;

}