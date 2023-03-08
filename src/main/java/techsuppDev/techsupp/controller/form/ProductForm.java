package techsuppDev.techsupp.controller.form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.domain.ProductStatus;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Data
public class ProductForm {

        private Long productId;

        private String productName;

        private int investPrice;

        private String totalPrice;

        private ProductStatus productStatus;

    }

