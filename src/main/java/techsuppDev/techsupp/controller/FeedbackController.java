package techsuppDev.techsupp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class FeedbackController {

// feedback service 만들어야함


    @GetMapping("/feedbackMain")
    public String LinkToFeedbackMain() {
        return "/feedback/feedbackMain";
    }


}
