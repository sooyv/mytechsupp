package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.controller.form.FeedbackSpecificListForm;
import techsuppDev.techsupp.domain.Feedback;
import techsuppDev.techsupp.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class FeedbackRepository {
    private final EntityManager em;

    public List<FeedbackSpecificListForm> findFeedbackList(Long productId) {
        String sql = "select img_url, feedback_status, feedback_text, product_id, score, user_id" +
                "from " +
                "feedback  inner join feedback_image" +
                "on feedback.id = feedback_image.feedback_id" +
                "where feedback.product_id = " +
                productId + ";";

        Query nativeQuery = em.createNativeQuery(sql, FeedbackSpecificListForm.class);
        List<FeedbackSpecificListForm> result = nativeQuery.getResultList();
        return result;
    }

}
