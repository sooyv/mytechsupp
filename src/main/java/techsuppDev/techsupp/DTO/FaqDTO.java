package techsuppDev.techsupp.DTO;


import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.domain.FaqEntity;
import techsuppDev.techsupp.domain.NoticeEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FaqDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqId;
    private String faqWriter;
    private String faqTitle;
    private String faqContents;
    private int faqHits;
//    private LocalDateTime noticeregDate;
//    private LocalDateTime noticemodDate;

//    private MultipartFile noticeFile; // save.html -> Controller 파일 담는 용도
//    private String originalFileName; // 원본 파일 이름
//    private String storedFileName; // 서버 저장용 파일 이름
//    private int fileAttached; // 파일 첨부 여부(첨부 1, 미첨부 0)

    public FaqDTO(Long faqId, String faqWriter, String faqTitle, int faqHits) {
        this.faqId = faqId;
        this.faqWriter = faqWriter;
        this.faqTitle = faqTitle;
        this.faqHits = faqHits;

    }

    public static FaqDTO toFaqDTO(FaqEntity faqEntity) {
        FaqDTO faqDTO = new FaqDTO();
        faqDTO.setFaqId(faqEntity.getFaqId());
        faqDTO.setFaqWriter(faqEntity.getFaqWriter());
        faqDTO.setFaqTitle(faqEntity.getFaqTitle());
        faqDTO.setFaqContents(faqEntity.getFaqContents());
        faqDTO.setFaqHits(faqEntity.getFaqHits());
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
        return faqDTO;
    }

}
