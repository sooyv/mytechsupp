package techsuppDev.techsupp.DTO;

import lombok.*;
import techsuppDev.techsupp.domain.NoticeEntity;

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
//    private LocalDateTime noticeregDate;
//    private LocalDateTime noticemodDate;

    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeId(noticeEntity.getNoticeId());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeHits(noticeEntity.getNoticeHits());
//        noticeDTO.setNoticeregDate(noticeEntity.NoticecregDate());
//        noticeDTO.setNoticemodDate(noticeEntity.getNoticemodDate());
        return noticeDTO;
    }


}
