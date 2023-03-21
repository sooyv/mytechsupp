package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.CommentDTO;
import techsuppDev.techsupp.domain.CommentEntity;
import techsuppDev.techsupp.domain.QuestionEntity;
//import techsuppDev.techsupp.domain.QuestionStatus;
import techsuppDev.techsupp.repository.CommentRepository;
import techsuppDev.techsupp.repository.QuestionRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final QuestionRepository questionRepository;

    public Long save(CommentDTO commentDTO) {
        // 부모엔티티 조회
        Optional<QuestionEntity> optionalQuestionEntity = questionRepository.findById(commentDTO.getQuestionId());
        System.out.println("test:" + optionalQuestionEntity);
        if (optionalQuestionEntity.isPresent()) {
            QuestionEntity questionEntity = optionalQuestionEntity.get();
//            System.out.println("test:" + questionEntity);
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, questionEntity);
            return commentRepository.save(commentEntity).getQuestionId();
            // 상태 바뀌는 로직
//            QuestionEntity.updateStatus(questionEntity);

        } else {
            return null;
        }
        
    }

    public List<CommentDTO> findAll(Long questionId) {
        QuestionEntity questionEntity = questionRepository.findById(questionId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByQuestionEntityOrderByQuestionIdDesc(questionEntity);
        /* EntityList -> DTOList */
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity, questionId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
