package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.CommentDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.*;
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
//            System.out.println("questionPass:" + questionEntity.getQuestionPass() );
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
            String savePath = "C:/project file/techsupp/src/main/resources/static/file/service" + storedFileName; // C:/springboot_img/12987489712_내사진 notice랑 충돌확인
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

    public Page<QuestionDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 10; // 한 페이지에 보여줄 글 갯수
        // 힌페이지당 3개씩 글을 보여주고 정렬 기준은 questionId 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0 부터 시작
        Page<QuestionEntity> questionEntities =
                questionRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "questionId")));
        System.out.println("questionEntities.getContent() = " + questionEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("questionEntities.getNumber() = " + questionEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("questionEntities.getTotalPages() = " + questionEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("questionEntities.getSize() = " + questionEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("questionEntities.hasPrevious() = " + questionEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("questionEntities.isFirst() = " + questionEntities.isFirst()); // 첫 페이지 여부
        System.out.println("questionEntities.isLast() = " + questionEntities.isLast()); // 마지막 페이지 여부

        // 목록: noticeid, writer, title, status,
        Page<QuestionDTO> questionDTOS = questionEntities.map(question -> new QuestionDTO(question.getQuestionId(),
                question.getQuestionWriter(), question.getQuestionTitle()));
        return questionDTOS;
    }

//    public void updateStatus(QuestionDTO questionDTO){
//        QuestionEntity questionStatus = QuestionEntity.updateStatus();
//    }

}
