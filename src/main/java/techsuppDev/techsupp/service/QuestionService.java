package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.repository.NoticeRepository;
import techsuppDev.techsupp.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public void save(QuestionDTO questionDTO) {
        QuestionEntity questionEntity = QuestionEntity.toSaveEntity(questionDTO);
        questionRepository.save(questionEntity);
    }


    public List<QuestionDTO> findAll() {
        List<QuestionEntity> questionEntityList = questionRepository.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (QuestionEntity questionEntity: questionEntityList) {
            questionDTOList.add(QuestionDTO.toquestionDTO(questionEntity));
        }
        return questionDTOList;

    }
}
