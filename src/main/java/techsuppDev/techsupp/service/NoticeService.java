package techsuppDev.techsupp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.repository.NoticeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;
    public void save(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }
    public List<NoticeDTO> findAll() {
        List<NoticeEntity> noticeEntityList = noticeRepository.findAll();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();
        for (NoticeEntity noticeEntity: noticeEntityList) {
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeEntity));
        }
        return noticeDTOList;
    }
    @Transactional
    public void updateHits(Long noticeId) {
        noticeRepository.updateHits(noticeId);
    }

    public NoticeDTO findById(Long noticeId) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(noticeId);
        if (optionalNoticeEntity.isPresent()) {
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;
        } else {
            return null;
        }
    }

    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
        return findById(noticeDTO.getNoticeId());
    }
}
