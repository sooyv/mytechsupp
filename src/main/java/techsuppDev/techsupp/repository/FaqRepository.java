package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import techsuppDev.techsupp.domain.FaqEntity;
import techsuppDev.techsupp.domain.NoticeEntity;

import java.util.List;

public interface FaqRepository extends JpaRepository<FaqEntity, Long> {

    List<FaqEntity> findAll();
    // update faq set faq_hits=faq_hits+1 where faqId=?
    @Modifying
    @Query(value = "update FaqEntity b set b.faqHits=b.faqHits+1 where b.faqId=:faqId")
    void updateHits(@Param("faqId") Long faqId);
}
