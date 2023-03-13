package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SqlResultSetMapping(
    name = "PaymentCountForm",
    columns = {
        @ColumnResult(name = "num0", type = int.class),
        @ColumnResult(name = "num1", type = int.class),
        @ColumnResult(name = "num2", type = int.class),
        @ColumnResult(name = "num3", type = int.class),
        @ColumnResult(name = "num4", type = int.class)
    }
)
public class PaymentCountForm {
    @Id
    private int num0;
    private int num1;
    private int num2;
    private int num3;
    private int num4;

}
