package techsuppDev.techsupp.domain;

import lombok.Getter;
import lombok.Setter;
import techsuppDev.techsupp.DTO.FaqDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "faq")
public class FaqEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long faqId;

    @Column(length = 20, nullable = false)
    private String faqWriter;

    @Column
    private String faqTitle;

    @Column(length = 500)
    private String faqContents;

    @Column
    private int faqHits;

//    @Column
//    private int fileAttached; // 1 or 0

//    @OneToMany(mappedBy = "noticeEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<NoticeFileEntity> noticeFileEntityList = new ArrayList<>();

    public static FaqEntity toSaveEntity(FaqDTO faqDTO) {
        FaqEntity faqEntity = new FaqEntity();
        faqEntity.setFaqWriter(faqDTO.getFaqWriter());
        faqEntity.setFaqTitle(faqDTO.getFaqTitle());
        faqEntity.setFaqContents(faqDTO.getFaqContents());
        faqEntity.setFaqHits(0);
//        noticeEntity.setFileAttached(0); // 파일 없음.
        return faqEntity;
    }

}
