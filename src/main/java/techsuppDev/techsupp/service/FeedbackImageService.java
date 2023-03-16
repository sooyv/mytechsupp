package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.domain.FeedbackImage;
import techsuppDev.techsupp.repository.FeedbackImageRepository;

@Service
@RequiredArgsConstructor
public class FeedbackImageService {
    private final FeedbackImageRepository feedbackImageRepository;

    public void insertFeedbackImageInformation (FeedbackImage feedbackImage) {
        feedbackImageRepository.insertImageToDb(feedbackImage);
    }

}
