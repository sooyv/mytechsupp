package techsuppDev.techsupp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class FeedbackImage {

    private Long feedbackId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String loadId;

    private Long seqId;

    private String imagePath;
    private String imageName;

    private Long imageSize;

    private String imageType;
}
