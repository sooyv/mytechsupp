package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.QuestionEntity;

import java.util.List;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    List<QuestionEntity> findByUserUserId(Long userId);

}
