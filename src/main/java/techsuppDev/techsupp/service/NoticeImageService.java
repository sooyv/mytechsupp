package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.repository.NoticeImageRepository;

@Service
@RequiredArgsConstructor
public class NoticeImageService {
    private final NoticeImageRepository noticeImageRepository;
}
