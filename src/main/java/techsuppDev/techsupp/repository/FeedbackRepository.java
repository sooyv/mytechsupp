package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import techsuppDev.techsupp.controller.form.FeedbackSpecificListForm;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class FeedbackRepository {
    private final EntityManager em;

    public List<FeedbackSpecificListForm> findFeedbackList(Long productId) {
        String sql = "" +
                "select " +
                "feedback.id, img_url, feedback_status, feedback_text, product_id, score, user_id " +
                "from " +
                "feedback " +
                "inner join feedback_image " +
                "on feedback.id = feedback_image.feedback_id " +
                "where feedback.product_id = " +
                productId + ";";

        System.out.println("feedbasdbas: " + sql);

        Query nativeQuery = em.createNativeQuery(sql, FeedbackSpecificListForm.class);
        List<FeedbackSpecificListForm> result = nativeQuery.getResultList();

        for(int i = 0; i < result.size() -1; i++) {
            System.out.println("result array " + result.get(i).getScore());
        }



        return result;
    }

}
