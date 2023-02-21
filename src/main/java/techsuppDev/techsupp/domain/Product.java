package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("i")
@Entity
@Getter
@Setter
//매핑 다시해야함
@SqlResultSetMapping(
        name = "ProductMapping",
        classes = @ConstructorResult(
                targetClass = Product.class,
                columns = {
                        @ColumnResult(name = "productId", type = Long.class),
                        @ColumnResult(name = "seqId", type = Long.class),
                        @ColumnResult(name = "productName", type = String.class),
                        @ColumnResult(name = "productInformation", type = String.class),
                        @ColumnResult(name = "productPrice", type = int.class),
                        @ColumnResult(name = "productPeriod", type = String.class),
                        @ColumnResult(name = "productPercent", type = int.class),
                        @ColumnResult(name = "productStatus", type = int.class),
                        @ColumnResult(name = "productDate", type = Date.class),
                        @ColumnResult(name = "productCount", type = int.class),
                })
)
@Table(name = "Product")
public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long ID;
//    String picture;
//    String product;
//    String investment;
//    String limitdate;
//    String percent;
//    String wish;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;
    Long seqId;
    String productName;
    String productInformation;
    int productPrice;
    String productPeriod;
    int productPercent;
    int productStatus;
    Date productDate;
    int productCount;
}
