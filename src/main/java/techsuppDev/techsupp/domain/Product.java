package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "seq_id", type = Long.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "information", type = String.class),
                        @ColumnResult(name = "total_price", type = int.class),
                        @ColumnResult(name = "invest_price", type = int.class),
                        @ColumnResult(name = "period", type = LocalDateTime.class),
                        @ColumnResult(name = "product_status", type = String.class),
                        @ColumnResult(name = "create_date", type = LocalDateTime.class),
                        @ColumnResult(name = "click_count", type = int.class)
                })
)
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seqId;
    private String productName;
    private String information;
    private int totalPrice;
    private int investPrice;
//    개인 투자액 컬럼 추가
    private LocalDateTime period;
    private String productStatus;
    private LocalDateTime createDate;
    private int clickCount;

//    모집인원 컬럼 추가
//    투자율 삭제
}
