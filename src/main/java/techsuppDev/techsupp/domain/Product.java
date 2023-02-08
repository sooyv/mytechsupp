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
