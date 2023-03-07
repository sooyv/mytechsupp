package techsuppDev.techsupp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import techsuppDev.techsupp.service.NoticeImageService;

@Controller
@AllArgsConstructor
public class NoticeImageController {
    private final NoticeImageService noticeImageService;
}
