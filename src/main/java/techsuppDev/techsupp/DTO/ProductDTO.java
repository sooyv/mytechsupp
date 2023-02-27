package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import techsuppDev.techsupp.domain.ProductStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private Long seqId;
    private String productName;
    private String information;
    private int totalPrice;
    private int investPrice;
    private LocalDate period;
    private ProductStatus productStatus;
    private int clickCount;
}
