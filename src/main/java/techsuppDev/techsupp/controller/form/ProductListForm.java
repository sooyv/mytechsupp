package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SqlResultSetMapping(
        name = "ProductListMapping",
        columns = {
        @ColumnResult(name = "id", type = Long.class),
        @ColumnResult(name = "moddate", type = LocalDateTime.class),
        @ColumnResult(name = "regdate", type = LocalDateTime.class),
        @ColumnResult(name = "click_count", type = int.class),
        @ColumnResult(name = "information", type = String.class),
        @ColumnResult(name = "invest_price", type = Integer.class),
        @ColumnResult(name = "period", type = LocalDate.class),
        @ColumnResult(name = "product_name", type = String.class),
        @ColumnResult(name = "product_status", type = String.class),
        @ColumnResult(name = "seq_id", type = Long.class),
        @ColumnResult(name = "total_price", type = Integer.class),
        @ColumnResult(name = "img_url", type = String.class)
        })
public class ProductListForm {
        @Id
        private Long id;
        private Long seqId;
        private String productName;
        private String information;
        private int totalPrice;
        private int investPrice;
        private LocalDate period;
        private String productStatus;
        private int clickCount;
        private String imgUrl;
    }

