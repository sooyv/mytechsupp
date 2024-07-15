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
    // noticeId에 해당하는 NoticeFileEntity 삭제
    void deleteByNoticeNoticeId(Long noticeId);

}
