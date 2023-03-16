package techsuppDev.techsupp.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.domain.FeedbackImage;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
@RequiredArgsConstructor
public class FeedbackImageRepository {
    private final EntityManager em;
    @Transactional
    @Modifying
    public void insertImageToDb(FeedbackImage feedbackImage) {
        String sql = "" +
                "insert into feedback_image " +
                "(img_name, origin_img_name, img_url, rep_img, id, feedback_id) " +
                "values ('" +
                feedbackImage.getImgName() + "', '" +
                feedbackImage.getOriginImgName() + "', '" +
                feedbackImage.getImgUrl() + "', '" +
                feedbackImage.getRepImg() + "', '" +
                feedbackImage.getId() + "', '" +
                feedbackImage.getFeedbackId() + "');";

        System.out.println("insert image feedback: " + sql);
        Query nativeQuery = em.createNativeQuery(sql, FeedbackImage.class);
        nativeQuery.executeUpdate();

        System.out.println("피드백 이미지 정보 입력 완료");


    }
}
