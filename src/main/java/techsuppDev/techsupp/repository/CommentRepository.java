package techsuppDev.techsupp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import techsuppDev.techsupp.domain.CommentEntity;
import techsuppDev.techsupp.domain.QuestionEntity;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

        // select * from comment_table where board_id=? order by id desc;
        List<CommentEntity> findAllByQuestionEntityOrderByQuestionIdDesc(QuestionEntity questionEntity);
}
