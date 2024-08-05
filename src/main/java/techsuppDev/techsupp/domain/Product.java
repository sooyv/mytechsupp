package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@DiscriminatorValue("i")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@SqlResultSetMapping(
    name = "ProductMapping",
    columns = {
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "moddate", type = LocalDateTime.class),
        @ColumnResult(name = "regdate", type = LocalDateTime.class),
        @ColumnResult(name = "click_count", type = int.class),
        @ColumnResult(name = "information", type = String.class),
        @ColumnResult(name = "invest_price", type = Integer.class),
        @ColumnResult(name = "period", type = LocalDate.class),
        @ColumnResult(name = "product_name", type = String.class),
        @ColumnResult(name = "product_status", type = String.class),
        @ColumnResult(name = "seq_id", type = Long.class),
        @ColumnResult(name = "total_price", type = Integer.class)
    })

@Table(name = "Product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "seq_id")
    private Long seqId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "information", length = 2000, nullable = false)
    private String information;
    @Column(name = "total_price", nullable = false)
    private int totalPrice;
    @Column(name = "invest_price")
    private int investPrice;
    @Column(name = "period")
    private LocalDate period;
    @Enumerated(EnumType.STRING)
    @Column(name = "product_status")
    private ProductStatus productStatus;
    @Column(name = "click_count", columnDefinition = "integer default 0", nullable = false)
    private int clickCount;

}
