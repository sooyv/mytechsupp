package techsuppDev.techsupp.controller.form;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class ProductForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long no;
    public String picture;
    public String product;
    public String investment;
    public String limitdate;
    public String percent;
    public String wish;

}
