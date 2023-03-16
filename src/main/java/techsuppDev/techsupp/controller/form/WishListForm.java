package techsuppDev.techsupp.controller.form;


import lombok.*;

import javax.persistence.*;
import techsuppDev.techsupp.domain.User;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "WishListForm")
@SqlResultSetMapping(
        name = "wishListFormMapping",
        columns = {
                @ColumnResult(name = "wish_id", type = Long.class),
                @ColumnResult(name = "product_id", type = Long.class),
                @ColumnResult(name = "user_id", type = Long.class)
        }
)
public class WishListForm {
    @Id
    private Long wishId;
    private String productId;
    private String userId;


}
