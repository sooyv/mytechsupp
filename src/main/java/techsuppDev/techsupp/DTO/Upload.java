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
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String uploadId;
    private int seqId;
    private String uploadPath;
    private String uploadName;
    private long uploadSize;
    private String uploadType;
    private int isImage;
}
