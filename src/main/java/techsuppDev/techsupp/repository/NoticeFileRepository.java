package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.NoticeFileEntity;

public interface NoticeFileRepository extends JpaRepository<NoticeFileEntity, Long> {
}
