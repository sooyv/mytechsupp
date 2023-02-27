package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedbackRepository {
    private final EntityManager em;

//    한개 select





























//    feedback test data create
//    public Object insertTestdataFeedback() {
//        String sql = " " +
//                "insert into product " +
//                "(product_id, seq_id, feedbackText, score, feedbackStatus) " +
//                "values ";
//        String middleSql = "(";
//        String endSql = ")";
//
//        String feedbackText;
//        String score;
//        String feedbackStatus;
//    }

}
