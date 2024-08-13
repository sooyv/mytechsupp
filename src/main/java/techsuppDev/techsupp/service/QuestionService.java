package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.*;;
import techsuppDev.techsupp.repository.QuestionFileRepository;
import techsuppDev.techsupp.repository.QuestionRepository;
import techsuppDev.techsupp.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionFileRepository questionFileRepository;
    private final UserRepository userRepository;
    @Value("${qnaServicePath}")
    String qnaServicePath;

    // 문의사항 작성
    public void questionResister(QuestionDTO questionDTO) throws IOException {
        System.out.println(questionDTO.getQuestionWriter());
        System.out.println(questionDTO.getQuestionTitle());
        System.out.println(questionDTO.getQuestionContents());
//        System.out.println("isPrivate : "+ questionDTO.getSecretPost());
        System.out.println("isPrivate : "+ questionDTO.isSecretPost());

        if (questionDTO.getQuestionFile().isEmpty() || questionDTO.getQuestionFile() == null) {
            // 첨부 파일 없음.

            // 세션에 존재하는 로그인한 유저가 있는지 맞는지 확인
            String userEmail = questionDTO.getQuestionWriter();
            Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
            User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found for email: " + userEmail));

            QuestionEntity questionEntity = QuestionEntity.toSaveEntity(questionDTO);
            questionEntity.setUser(user);
            questionRepository.save(questionEntity);
        } else {
            // 첨부파일 있음
            saveQuestionFile(questionDTO);
        }
    }

    // 첨부파일 저장 로직 - 문의사항
    public QuestionEntity saveQuestionFile(QuestionDTO questionDTO) throws IOException {

        MultipartFile questionFile = questionDTO.getQuestionFile();
        String originalFilename = questionFile.getOriginalFilename();
        String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
        String savePath = qnaServicePath + storedFileName;
        questionFile.transferTo(new File(savePath));

        // 세션에 존재하는 로그인한 유저가 있는지 맞는지 확인
        String userEmail = questionDTO.getQuestionWriter();
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found for email: " + userEmail));

        // 첨부 파일이 있을때 toSaveFileEntity로 첨부파일 추가 questionEntity 변환
        QuestionEntity questionEntity = QuestionEntity.toSaveFileEntity(questionDTO, user);
        questionRepository.save(questionEntity);         // 저장

        QuestionFileEntity questionFileEntity = QuestionFileEntity.toQuestionFileEntity(questionEntity, originalFilename, storedFileName);
        questionFileRepository.save(questionFileEntity);

        return questionFileEntity.getQuestionEntity();
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

    public Page<QuestionDTO> paging(Pageable pageable, String currentUserEmail, boolean isAuthenticated) {
        // 페이지 번호와 페이지 크기 설정
        int page = pageable.getPageNumber() - 1; // 페이지 번호는 0부터 시작
        int pageLimit = pageable.getPageSize(); // 페이지당 항목 수

        // 페이지 요청
        Page<QuestionEntity> questionEntities = questionRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "questionId")));

        // 페이지 데이터 변환
        Page<QuestionDTO> questionDTO = questionEntities.map(question -> {
            QuestionDTO dto = new QuestionDTO(
                    question.getQuestionId(),
                    question.getQuestionStatus(),
                    question.getQuestionTitle(),
                    question.getUser().getUserName(),
                    question.getCreatedAtQ(),
                    question.isSecretPost()
            );

            // 비밀글 처리
            if (!isAuthenticated || !currentUserEmail.equals(question.getUser().getUserEmail())) {
                // 로그인하지 않았거나 현재 사용자가 작성자가 아닌 비밀글의 경우 제목을 "비밀글입니다"로 변경
                if (question.isSecretPost()) {
                    dto.setQuestionTitle("비밀글입니다");
//                    dto.setQuestionContents(null); // 비밀글 내용은 숨김
                }
            }

            return dto;
        });

        return questionDTO;
    }

//    public void updateStatus(QuestionDTO questionDTO){
//        QuestionEntity questionStatus = QuestionEntity.updateStatus();
//    }

}
