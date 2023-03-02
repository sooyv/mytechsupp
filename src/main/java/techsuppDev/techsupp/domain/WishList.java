package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "WishList")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;
//    @ManyToOne
    private Long userId;
//    @ManyToOne
//    @JoinColumn(name = "id")
    private Long productId;
}
