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
                "feedback.id, img_url, feedback_status, feedback_text, product_id, score, user_email " +
                "from feedback " +
                "inner join feedback_image " +
                "on feedback.id = feedback_image.feedback_id " +
                "left outer join " +
                "(select user_email, userid from user inner join feedback " +
                "on feedback.user_id = user.userid) as emailvalue " +
                "on feedback.user_id = emailvalue.userid " +
                "where feedback.product_id = " +
                productId + ";";

        System.out.println("feedbasdbas: " + sql);

        Query nativeQuery = em.createNativeQuery(sql, FeedbackSpecificListForm.class);
        List<FeedbackSpecificListForm> result = nativeQuery.getResultList();
        return result;
    }

}
