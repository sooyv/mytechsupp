package techsuppDev.techsupp.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import techsuppDev.techsupp.domain.Image;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductImgDTO {
    private Long id;
    private String originImgName;
    private String storedImgName;
    private String imgUrl;
    private String regImg;


    public Image dtoToEntity(ProductImgDTO dto) {
        Image entity = Image.builder()
                .imgId(dto.id)
                .originImgName(dto.originImgName)
                .storedImgName(dto.storedImgName)
                .imgUrl(dto.imgUrl)
                .repImg(dto.regImg).build();

        return entity;
    }

    public static ProductImgDTO entityToDto(Image entity) {
        ProductImgDTO dto = ProductImgDTO.builder()
                .id(entity.getImgId())
                .originImgName(entity.getOriginImgName())
                .storedImgName(entity.getStoredImgName())
                .imgUrl(entity.getImgUrl())
                .regImg(entity.getRepImg()).build();

        return dto;
    }

}
