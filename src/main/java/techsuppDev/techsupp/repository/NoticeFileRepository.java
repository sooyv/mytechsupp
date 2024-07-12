package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.domain.NoticeFileEntity;

import java.util.Optional;

@Repository
public interface NoticeFileRepository extends JpaRepository<NoticeFileEntity, Long> {

    @Query(value = "SELECT nf FROM NoticeFileEntity nf WHERE nf.notice.noticeId = :noticeId",  nativeQuery = true)
    NoticeFileEntity findByNoticeId(@Param("noticeId") Long noticeId);

    @Modifying
    @Query(value = "DELETE FROM notice_file WHERE notice_id = :noticeId", nativeQuery = true)
    void deleteAttachedFileByNoticeId(@Param("noticeId") Long noticeId);
}
