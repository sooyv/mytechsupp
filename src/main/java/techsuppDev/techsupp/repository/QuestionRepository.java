package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

}
