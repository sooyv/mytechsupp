package techsuppDev.techsupp.domain;

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
@Table(name = "WishList")
@SqlResultSetMapping(
    name = "wishListMapping",
    columns = {
        @ColumnResult(name = "wish_id", type = Long.class),
        @ColumnResult(name = "product_id", type = Long.class),
        @ColumnResult(name = "user_id", type = Long.class)
    }
)
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

//    private Long userId;

//    private Long productId;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    @JoinColumn( name = "product_id")
    private Product product;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
