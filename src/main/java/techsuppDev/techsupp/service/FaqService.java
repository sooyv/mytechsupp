package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.DTO.FaqDTO;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.domain.FaqEntity;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.repository.FaqRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

    public void faqResister(FaqDTO faqDTO) throws IOException {
        FaqEntity faqEntity = FaqEntity.toSaveEntity(faqDTO);
        faqRepository.save(faqEntity);
    }

    @Transactional
    public FaqEntity faqUpdate(FaqDTO faqDTO) throws IOException {
        FaqEntity faqEntity = FaqEntity.toUpdateEntity(faqDTO);
        return faqRepository.save(faqEntity);
    }

    @Transactional
    public Long deleteFaq(Long faqId) {
        faqRepository.deleteById(faqId);
        return faqId;
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
