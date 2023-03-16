package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import techsuppDev.techsupp.domain.Image;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDTO {
    private Long id;
    private String imgName;
    private String originImgName;
    private String imgUrl;
    private String regImg;


    public Image dtoToEntity(ProductImgDTO dto) {
        Image entity = Image.builder()
                .id(dto.id)
                .imgName(dto.imgName)
                .originImgName(dto.originImgName)
                .imgUrl(dto.imgUrl)
                .repImg(dto.regImg).build();

        return entity;
    }

    public static ProductImgDTO entityToDto(Image entity) {
        ProductImgDTO dto = ProductImgDTO.builder()
                .id(entity.getId())
                .imgName(entity.getImgName())
                .originImgName(entity.getOriginImgName())
                .imgUrl(entity.getImgUrl())
                .regImg(entity.getRepImg()).build();

        return dto;
    }

}
