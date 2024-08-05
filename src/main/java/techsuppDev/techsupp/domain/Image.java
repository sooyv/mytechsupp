

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
    @Column(name = "img_id")
    private Long imgId;
    private String originImgName;
    private String storedImgName;
    private String imgUrl;
    private String repImg;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public void updateProductImg(String originImgName, String storedImgName, String imgUrl) {
        this.originImgName = originImgName;
        this.storedImgName = storedImgName;
        this.imgUrl = imgUrl;
    }
}