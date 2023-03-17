package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.FaqDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.domain.FaqEntity;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.domain.NoticeFileEntity;
import techsuppDev.techsupp.domain.QuestionEntity;
import techsuppDev.techsupp.repository.FaqRepository;
import techsuppDev.techsupp.repository.NoticeFileRepository;
import techsuppDev.techsupp.repository.NoticeRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

    public void save(FaqDTO faqDTO) throws IOException {

        FaqEntity faqEntity = FaqEntity.toSaveEntity(faqDTO);
        faqRepository.save(faqEntity);


    }

    @Transactional
    public List<FaqDTO> findAll() {

        List<FaqEntity> faqEntityList = faqRepository.findAll();
        List<FaqDTO> faqDTOList = new ArrayList<>();
        for (FaqEntity faqEntity: faqEntityList) {
            faqDTOList.add(FaqDTO.toFaqDTO(faqEntity));
        }
        return faqDTOList;
    }
    @Transactional
    public void updateHits(Long faqId) {

        faqRepository.updateHits(faqId);
    }

    @Transactional
    public FaqDTO findById(Long faqId) {
        Optional<FaqEntity> optionalFaqEntity = faqRepository.findById(faqId);
        if (optionalFaqEntity.isPresent()) {
            FaqEntity faqEntity = optionalFaqEntity.get();
            FaqDTO faqDTO = FaqDTO.toFaqDTO(faqEntity);
            return faqDTO;
        } else {
            return null;
        }
    }
}
