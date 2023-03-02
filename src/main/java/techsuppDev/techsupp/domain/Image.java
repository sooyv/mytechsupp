

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
    private String uuid;
    private String imageName;
    private String path;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}