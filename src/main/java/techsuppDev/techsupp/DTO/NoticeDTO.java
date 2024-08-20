package techsuppDev.techsupp.DTO;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.domain.NoticeEntity;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO extends ServiceDTO {
    private int noticeHits;
    private MultipartFile noticeFile;


    public NoticeDTO(Long postId, String userEmail, String postTitle, int noticeHits) {
        super.setPostId(postId);
        super.setUserEmail(userEmail);
        super.setPostTitle(postTitle);
        this.noticeHits = noticeHits;
    }

    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setPostId(noticeEntity.getNoticeId());
        noticeDTO.setUserEmail(noticeEntity.getNoticeWriter());
        noticeDTO.setPostTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setPostContents(noticeEntity.getNoticeContents());
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
