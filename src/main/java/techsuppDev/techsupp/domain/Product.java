package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@DiscriminatorValue("i")
@AllArgsConstructor
@Entity
@Builder
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
                        @ColumnResult(name = "status", type = int.class),
                        @ColumnResult(name = "create_date", type = LocalDateTime.class),
                        @ColumnResult(name = "click_count", type = int.class)
                })
)
@Table(name = "Product")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seqId;
    @Column(nullable = false)
    private String productName;

    @Column(length = 2000, nullable = false)
    private String information;
    @Column(nullable = false)
    private int totalPrice;
    @Column(nullable = false)
    private int investPrice;
//    개인 투자액 컬럼 추가
    private LocalDate period;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
    @Column(columnDefinition = "integer default 0", nullable = false)
    private int clickCount;
//    모집인원 컬럼 추가
//    투자율 삭제
}
