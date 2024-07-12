package techsuppDev.techsupp.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
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

    private MultipartFile noticeFile; // save.html -> Controller 파일 담는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public NoticeDTO(Long noticeId, String noticeWriter, String noticeTitle, int noticeHits) {
        this.noticeId = noticeId;
        this.noticeWriter = noticeWriter;
        this.noticeTitle = noticeTitle;
        this.noticeHits = noticeHits;

    }


    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setNoticeId(noticeEntity.getNoticeId());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeHits(noticeEntity.getNoticeHits());
//        noticeDTO.setNoticeregDate(noticeEntity.NoticecregDate());
//        noticeDTO.setNoticemodDate(noticeEntity.getNoticemodDate());
//        if (noticeEntity.getFileAttached() == 0) {
//            noticeDTO.setFileAttached(noticeEntity.getFileAttached()); // 0
//        } else {
//            noticeDTO.setFileAttached(noticeEntity.getFileAttached()); // 1
//            // 파일 이름을 가져가야 함.
//            noticeDTO.setOriginalFileName(noticeEntity.getNoticeFileEntityList().get(0).getOriginalFileName());
//            noticeDTO.setStoredFileName(noticeEntity.getNoticeFileEntityList().get(0).getStoredFileName());
//
//        }

        if (noticeEntity.getNoticeFile() != null) {
            noticeDTO.setOriginalFileName(noticeEntity.getNoticeFile().getOriginalFileName());
            noticeDTO.setStoredFileName(noticeEntity.getNoticeFile().getStoredFileName());
        }

        return noticeDTO;
    }


}
