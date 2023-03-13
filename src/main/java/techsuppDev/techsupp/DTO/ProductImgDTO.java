package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import techsuppDev.techsupp.domain.Image;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ProductImgDTO {
    private Long imageNum;
    private String imgName;
    private String originImgName;
    private String imgUrl;
    private String regImg;

    public Image dtoToEntity(ProductImgDTO dto) {
        Image entity = Image.builder()
                .imgName(dto.imgName)
                .originImgName(dto.originImgName)
                .imgUrl(dto.imgUrl)
                .repImg(dto.regImg).build();

        return entity;
    }

    public ProductImgDTO entityToDto(Image entity) {
        ProductImgDTO dto = ProductImgDTO.builder()
                .imgName(entity.getImgName())
                .originImgName(entity.getOriginImgName())
                .imgUrl(entity.getImgUrl())
                .regImg(entity.getRepImg()).build();

        return dto;
    }
}
