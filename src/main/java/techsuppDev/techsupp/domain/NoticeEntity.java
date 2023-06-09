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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
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

    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<NoticeFileEntity> noticeFileEntityList = new ArrayList<>();

    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        noticeEntity.setFileAttached(0); // 파일 없음.
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

    public static NoticeEntity toSaveFileEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        noticeEntity.setFileAttached(1); // 파일있음.
        return noticeEntity;

    }



}
