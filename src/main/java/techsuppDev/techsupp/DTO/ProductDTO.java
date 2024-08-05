package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import techsuppDev.techsupp.domain.Product;
import techsuppDev.techsupp.domain.ProductStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private String information;
    private int totalPrice;
    private int investPrice;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate period;
    private ProductStatus productStatus;
    private int clickCount;
    @Builder.Default
    private List<ProductImgDTO> productImgDTOList = new ArrayList<>();
    @Builder.Default
    private List<Long> productImgIds = new ArrayList<>();


    public Product dtoToEntity(ProductDTO productDTO) {
        Product entity = Product.builder()
                .productId(productDTO.getId())
                .productName(productDTO.getProductName())
                .totalPrice(productDTO.getTotalPrice())
                .period(productDTO.getPeriod())
                .information(productDTO.getInformation())
                .investPrice(productDTO.getInvestPrice()).build();
        return entity;
    }

    public static ProductDTO entityToDto(Product entity) {
        ProductDTO dto = ProductDTO.builder()
                .id(entity.getProductId())
                .productName(entity.getProductName())
                .totalPrice(entity.getTotalPrice())
                .period(entity.getPeriod())
                .information(entity.getInformation())
                .productStatus(entity.getProductStatus())
                .investPrice(entity.getInvestPrice())
                .clickCount(entity.getClickCount()).build();
        return dto;
    }

    public String getInformation() {
//        return information.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        return information.replaceAll("\\<.*?\\>", "");

    }
}
