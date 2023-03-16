package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.controller.form.FeedbackSpecificListForm;
import techsuppDev.techsupp.domain.Feedback;

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
                "feedback.id, img_url, feedback_status, feedback_text, product_id, score, user_name " +
                "from feedback " +
                "inner join feedback_image " +
                "on feedback.id = feedback_image.feedback_id " +
                "left outer join " +
                "(select user_name, userid from user inner join feedback " +
                "on feedback.user_id = user.userid) as emailvalue " +
                "on feedback.user_id = emailvalue.userid " +
                "where feedback.product_id = " +
                productId + ";";

        System.out.println("feedbasdbas: " + sql);

        Query nativeQuery = em.createNativeQuery(sql, FeedbackSpecificListForm.class);
        List<FeedbackSpecificListForm> result = nativeQuery.getResultList();
        return result;
    }

    @Transactional
    @Modifying
    public void insertFeedbackToDb(Feedback feedbackForm) {
        String sql = "" +
                "insert into feedback " +
                "(feedback_status, feedback_text, product_id, score, user_id) " +
                "values ('" +
                feedbackForm.getFeedbackStatus() + "', '" +
                feedbackForm.getFeedbackText() + "', " +
                feedbackForm.getProductId() + ", " +
                feedbackForm.getScore() + ", " +
                feedbackForm.getUserId() + ");";

        System.out.println("feedback insert: " + sql);

        Query nativeQuery = em.createNativeQuery(sql, Feedback.class);
        nativeQuery.executeUpdate();

        System.out.println("feedback insert 가 완료 되었습니다.");

    }

    public Long getFeedbackId() {
        String sql = "" +
                "select last_insert_id();";

        Query nativeQuery = em.createNativeQuery(sql);
        Long singlePayment = Long.parseLong(nativeQuery.getSingleResult().toString()) ;
        return singlePayment;
    }



}
