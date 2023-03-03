package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice_file")
public class NoticeFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    @Column
    private String originalFileName;

    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_notice_id")
    private NoticeEntity noticeEntity;

    public static NoticeFileEntity toNoticeFileEntity(NoticeEntity noticeEntity, String originalFileName, String storedFileName) {
        NoticeFileEntity noticeFileEntity = new NoticeFileEntity();
        noticeFileEntity.setOriginalFileName(originalFileName);
        noticeFileEntity.setStoredFileName(storedFileName);
        noticeFileEntity.setNoticeEntity(noticeEntity);
        return noticeFileEntity;
    }

}
