package techsuppDev.techsupp.DTO;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;
    private String noticeWriter;
    private String noticeTitle;
    private String noticeContents;
    private int noticeHits;
    private LocalDateTime noticeregDate;
    private LocalDateTime noticemodDate;


}
