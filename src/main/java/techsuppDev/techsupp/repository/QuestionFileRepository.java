package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.QuestionFileEntity;

import java.util.List;

public interface QuestionFileRepository extends JpaRepository<QuestionFileEntity, Long> {
}
