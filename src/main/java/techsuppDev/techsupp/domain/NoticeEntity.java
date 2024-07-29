package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.NoticeDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    @Column(length = 20, nullable = false)
    private String noticeWriter;

    @Column
    private String noticeTitle;

    @Column(length = 500)
    private String noticeContents;

    @Column
    private int noticeHits;

    @Column
    private int fileAttached; // 1 or 0

    @OneToOne(mappedBy = "notice", cascade = CascadeType.ALL, orphanRemoval = true)
    private NoticeFileEntity noticeFile;

    // 첨부파일이 없을때 엔티티
    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        noticeEntity.setFileAttached(0); // 파일 없음.
        return noticeEntity;
    }

    // 첨부파일이 있을때 엔티티
    public static NoticeEntity toSaveFileEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        noticeEntity.setFileAttached(1); // 파일있음.
        return noticeEntity;
    }

    public static NoticeEntity toUpdateEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeId(noticeDTO.getNoticeId());
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
//        noticeEntity.setNoticeHits(noticeEntity.getNoticeHits());
        return noticeEntity;
    }
}
