package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("i")
@Entity
@Getter
@Setter
@SqlResultSetMapping(
        name = "ProductMapping",
        classes = @ConstructorResult(
                targetClass = Product.class,
                columns = {
                        @ColumnResult(name="id", type = Long.class),
                        @ColumnResult(name = "investment", type = String.class),
                        @ColumnResult(name = "limitdate", type = String.class),
                        @ColumnResult(name = "percent", type = String.class),
                        @ColumnResult(name = "picture", type = String.class),
                        @ColumnResult(name = "product", type = String.class),
                        @ColumnResult(name = "wish", type = String.class)
                })
)
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ID;
    String picture;
    String product;
    String investment;
    String limitdate;
    String percent;
    String wish;
}
