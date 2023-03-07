package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.domain.QuestionFileEntity;
import techsuppDev.techsupp.repository.NoticeRepository;
import techsuppDev.techsupp.repository.QuestionFileRepository;
import techsuppDev.techsupp.repository.QuestionRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionFileRepository questionFileRepository;

    public void save(QuestionDTO questionDTO) throws IOException {

        if (questionDTO.getQuestionFile().isEmpty()) {
            // 첨부 파일 없음.
            QuestionEntity questionEntity = QuestionEntity.toSaveEntity(questionDTO);
            questionRepository.save(questionEntity);
        } else {
            // 첨부 파일 있음.
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                // 내사진.jpg => 839798375892_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. question에 해당 데이터 save 처리
                7. question_file에 해당 데이터 save 처리
             */
            MultipartFile questionFile = questionDTO.getQuestionFile(); // 1.
            String originalFilename = questionFile.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "C:/springboot_img/" + storedFileName; // C:/springboot_img/12987489712_내사진 notice랑 충돌확인 5.
            questionFile.transferTo(new File(savePath)); // 5.
            QuestionEntity questionEntity = QuestionEntity.toSaveFileEntity(questionDTO);
            Long savedQuestionId = questionRepository.save(questionEntity).getQuestionId();
            QuestionEntity question = questionRepository.findById(savedQuestionId).get();

            QuestionFileEntity questionFileEntity = QuestionFileEntity.toQuestionFileEntity(question, originalFilename, storedFileName);
            questionFileRepository.save(questionFileEntity);


        }

    }

    @Transactional
    public List<QuestionDTO> findAll() {
        List<QuestionEntity> questionEntityList = questionRepository.findAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (QuestionEntity questionEntity: questionEntityList) {
            questionDTOList.add(QuestionDTO.toQuestionDTO(questionEntity));
        }
        return questionDTOList;

    }

    @Transactional
    public QuestionDTO findById(Long questionId) {
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(questionId);
        if (optionalQuestionEntity.isPresent()) {
            QuestionEntity questionEntity = optionalQuestionEntity.get();
            QuestionDTO questionDTO = QuestionDTO.toQuestionDTO(questionEntity);
            return questionDTO;
        } else {
            return null;
        }
    }
    public QuestionDTO update(QuestionDTO questionDTO) {

        QuestionEntity questionEntity = QuestionEntity.toUpdateEntity(questionDTO);
        questionRepository.save(questionEntity);
        return findById(questionDTO.getQuestionId());
    }
}
