package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.NoticeDTO;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "notice_table")
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

    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        return noticeEntity;
    }

    public static NoticeEntity toUpdateEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = new NoticeEntity();
        noticeEntity.setNoticeId(noticeDTO.getNoticeId());
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(noticeEntity.getNoticeHits());
        return noticeEntity;

    }



}
