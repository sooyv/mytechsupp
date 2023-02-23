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
                        @ColumnResult(name = "seqId", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "information", type = String.class),
                        @ColumnResult(name = "totalPrice", type = int.class),
                        @ColumnResult(name = "investPrice", type = int.class),
                        @ColumnResult(name = "period", type = LocalDateTime.class),
                        @ColumnResult(name = "status", type = int.class),
                        @ColumnResult(name = "createDate", type = LocalDateTime.class),
                        @ColumnResult(name = "clickCount", type = int.class),
                        @ColumnResult(name = "recruit", type = int.class),
                })
)
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seqId;
    private String name;
    private String information;
    private int totalPrice;
    private int investPrice;
//    개인 투자액 컬럼 추가
    private LocalDateTime period;
    private int status;
    private LocalDateTime createDate;
    private int clickCount;
    private int recruit;
//    모집인원 컬럼 추가
//    투자율 삭제
}
