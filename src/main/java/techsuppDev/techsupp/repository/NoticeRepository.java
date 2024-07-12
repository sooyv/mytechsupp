package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.domain.User;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    List<NoticeEntity> findAll();
    // update notice_table set notice_hits=notice_hits+1 where id=?
    @Modifying
    @Query(value = "update NoticeEntity b set b.noticeHits=b.noticeHits+1 where b.noticeId=:noticeId")
    void updateHits(@Param("noticeId") Long noticeId);

    @Modifying
    @Query(value = "DELETE FROM notice WHERE notice_id = :noticeId", nativeQuery = true)
    void deleteByNotionId(@Param("noticeId") Long noticeId);

}
