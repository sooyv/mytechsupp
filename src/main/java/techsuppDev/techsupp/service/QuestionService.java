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
import techsuppDev.techsupp.DTO.QuestionAnswerDTO;
import techsuppDev.techsupp.DTO.QuestionDTO;
import techsuppDev.techsupp.domain.*;;
import techsuppDev.techsupp.repository.QuestionFileRepository;
import techsuppDev.techsupp.repository.QuestionRepository;
import techsuppDev.techsupp.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        if (questionDTO.getQuestionFile().isEmpty() || questionDTO.getQuestionFile() == null) {
            // 첨부 파일 없음.

            // 세션에 존재하는 로그인한 유저가 있는지 맞는지 확인
            String userEmail = questionDTO.getUserEmail();
            System.out.println("이메일 확인 : " + questionDTO.getUserEmail());
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
        System.out.println("questionFile"+ questionFile);
        String originalFilename = questionFile.getOriginalFilename();
        System.out.println("originalFilename"+ originalFilename);
        String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
        System.out.println("storedFileName"+ storedFileName);
        String savePath = qnaServicePath + storedFileName;
        System.out.println("savePath"+ savePath);
        questionFile.transferTo(new File(savePath));

        // 세션에 존재하는 로그인한 유저가 있는지 확인
        String userEmail = questionDTO.getUserEmail();
        Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
        User user = optionalUser.orElseThrow(() -> new IllegalArgumentException("User not found for email: " + userEmail));

        // 첨부 파일이 있을때 toSaveFileEntity로 첨부파일 추가 questionEntity 변환
        QuestionEntity questionEntity = QuestionEntity.toSaveFileEntity(questionDTO, user);
        questionRepository.save(questionEntity);         // 저장

        QuestionFileEntity questionFileEntity = QuestionFileEntity.toQuestionFileEntity(questionEntity, originalFilename, storedFileName);
        questionFileRepository.save(questionFileEntity);

        return questionEntity;
    }

    @Transactional
    public QuestionEntity updateQuestion(QuestionDTO questionDTO, Long questionId) throws IOException {
        QuestionEntity questionEntity = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("문의 사항을 찾을 수 없습니다."));

        questionEntity.updateFromQuestionDTO(questionDTO);
        System.out.println("title 확인 : " + questionDTO.getPostTitle());
        System.out.println("contents 확인 : " + questionDTO.getPostContents());

        // 첨부파일 업데이트
        updateQuestionFile(questionDTO, questionEntity);

        // 수정된 문의 사항 저장
        return questionRepository.save(questionEntity);
    }

    public void updateQuestionFile(QuestionDTO questionDTO, QuestionEntity questionEntity) throws IOException {
        MultipartFile questionFile = questionDTO.getQuestionFile();
        QuestionFileEntity existingFile = questionEntity.getQuestionFileEntity();

        if (questionFile != null && !questionFile.isEmpty()) {
            // 새 파일이 업로드된 경우
            if (existingFile != null) {
                // 기존 파일 삭제
                deleteQuestionFile(questionDTO.getPostId());
                // 기존 파일 정보 삭제
                questionFileRepository.delete(existingFile);
            }

            // 새로운 파일 저장
            String originalFilename = questionFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = qnaServicePath + storedFileName;
            questionFile.transferTo(new File(savePath));

            // 새로운 파일 정보 저장
            QuestionFileEntity newFile = new QuestionFileEntity();
            newFile.setOriginalFileName(originalFilename);
            newFile.setStoredFileName(storedFileName);
            newFile.setQuestionEntity(questionEntity);
            questionFileRepository.save(newFile);

            questionEntity.setFileAttached(1);
        } else if (existingFile != null) {
            // 기존 파일이 있지만 새 파일이 없는 경우
            questionEntity.setFileAttached(1);
        } else {
            // 첨부파일이 없는 경우
            questionEntity.setFileAttached(0);
        }
    }



    @Transactional
    public void deleteQuestionFile(Long questionId) {
        Optional<QuestionEntity> questionEntityOptional = questionRepository.findById(questionId);

        if (questionEntityOptional.isPresent()) {
            QuestionEntity questionEntity = questionEntityOptional.get();
            QuestionFileEntity questionFile = questionEntity.getQuestionFileEntity();

            if (questionFile != null) {
                questionEntity.setQuestionFileEntity(null);
                questionFileRepository.delete(questionFile);

                File file = new File(qnaServicePath + questionFile.getStoredFileName());
                if (file.exists()) {
                    file.delete();
                }
            }
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
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        return QuestionDTO.toQuestionDTO(question);
    }

    // 문의 사항 단일 답변 가져오기
    public QuestionDTO findByIdWithAnswer(Long questionId) {
        QuestionEntity question = questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question not found"));

        QuestionDTO questionDTO = QuestionDTO.toQuestionDTO(question);
        System.out.println("findByIdWithAnswer 확인 1 : "+ questionDTO.getOriginalFileName());

        if (question.getQuestionAnswer() != null) {
            QuestionAnswerDTO answerDTO = QuestionAnswerDTO.fromEntity(question.getQuestionAnswer());
            questionDTO.setQuestionAnswer(answerDTO);
        }
        return questionDTO;
    }

    // 사용자 ID로 질문 리스트 조회
    public List<QuestionDTO> getQuestionsByUserId(Long userId) {
        List<QuestionEntity> questionEntities = questionRepository.findByUserUserId(userId);
        return questionEntities.stream()
                .map(this::toQuestionDTOWithAnswer) // 각 질문과 답변을 포함시킴
                .collect(Collectors.toList());
    }

    // QuestionEntity QuestionDTO로 변환, 리스트
    private QuestionDTO toQuestionDTOWithAnswer(QuestionEntity questionEntity) {
        QuestionDTO questionDTO = QuestionDTO.toQuestionDTO(questionEntity);

        if (questionEntity.getQuestionAnswer() != null) {
            QuestionAnswerDTO answerDTO = QuestionAnswerDTO.fromEntity(questionEntity.getQuestionAnswer());
            questionDTO.setQuestionAnswer(answerDTO);
        }

        return questionDTO;
    }



    public QuestionDTO update(QuestionDTO questionDTO) {

        QuestionEntity questionEntity = QuestionEntity.toUpdateEntity(questionDTO);
        questionRepository.save(questionEntity);
        return findById(questionDTO.getPostId());
    }


    public Page<QuestionDTO> paging(Pageable pageable, String currentUserEmail, boolean isAuthenticated) {
        // 페이지 번호와 페이지 크기 설정
        int page = pageable.getPageNumber() - 1; // 페이지 번호는 0부터 시작
        int pageLimit = pageable.getPageSize();  // 페이지당 항목 수

        // 페이지 요청: 지정된 페이지 번호와 페이지 크기, 정렬 기준으로 QuestionEntity를 조회
        Page<QuestionEntity> questionEntities = questionRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "questionId")));

        // 페이지 데이터 변환: QuestionEntity를 QuestionDTO로 변환
        Page<QuestionDTO> questionDTOPage = questionEntities.map(question -> {
            QuestionDTO questionDTO = new QuestionDTO(
                    question.getQuestionId(),
                    question.getQuestionStatus(),
                    question.getQuestionTitle(),
                    question.getUser().getUserName(),
                    question.getUser().getUserEmail(),
                    question.getCreatedAtQ(),
                    question.isSecretPost()
            );

            // 비밀글 처리
            if (!isAuthenticated || !currentUserEmail.equals(question.getUser().getUserEmail())) {
                // 로그인하지 않았거나 현재 사용자가 작성자가 아닌 비밀글의 경우
                if (question.isSecretPost()) {
                    questionDTO.setPostTitle("비밀글입니다");
                }
            }
            return questionDTO;
        });

        // 변환된 DTO 페이지 반환
        return questionDTOPage;
    }

}
