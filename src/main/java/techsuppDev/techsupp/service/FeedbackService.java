package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.controller.form.FeedbackSpecificListForm;
import techsuppDev.techsupp.domain.Feedback;
import techsuppDev.techsupp.repository.FeedbackRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    public final FeedbackRepository feedbackRepository;

    public List<FeedbackSpecificListForm> findSpecificFeedback(Long productId) {
        return feedbackRepository.findFeedbackList(productId);
    }

    public void insertFeedback (Feedback feedbackForm) {
        feedbackRepository.insertFeedbackToDb(feedbackForm);
    }

    public Long getInsertedFeedbackId () {
        return feedbackRepository.getFeedbackId();
    }
}
