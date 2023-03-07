

package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "product")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageNum;
    private String imgName;
    private String originImgName;
    private String imgUrl;
    private String repImg;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Product product;

    public void updateProductImg(String imgName, String originImgName, String imgUrl) {
        this.imgName = imgName;
        this.originImgName = originImgName;
        this.imgUrl = imgUrl;
    }
}