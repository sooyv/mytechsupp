package techsuppDev.techsupp.DTO;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seqId;
}
