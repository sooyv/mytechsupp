package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.repository.NoticeRepository;
import techsuppDev.techsupp.repository.QuestionRepository;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void save(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = QuestionEntity.toSaveEntity(questionDTO);
        questionRepository.save(questionEntity);
    }
}
