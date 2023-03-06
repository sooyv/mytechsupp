package techsuppDev.techsupp.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.domain.NoticeFileEntity;
import techsuppDev.techsupp.repository.NoticeFileRepository;
import techsuppDev.techsupp.repository.NoticeRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    private final NoticeFileRepository noticeFileRepository;
    public void save(NoticeDTO noticeDTO) throws IOException {

        if (noticeDTO.getNoticeFile().isEmpty()) {
            // 첨부 파일 없음.
            NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
            noticeRepository.save(noticeEntity);
        } else {
            // 첨부 파일 있음.
            /*
                1. DTO에 담긴 파일을 꺼냄
                2. 파일의 이름 가져옴
                3. 서버 저장용 이름을 만듦
                // 내사진.jpg => 839798375892_내사진.jpg
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. notice_table에 해당 데이터 save 처리
                7. notice_file_table에 해당 데이터 save 처리
             */

//          for(MultipartFile noticeFile: noticeDTO.getNoticeFile()) {
            MultipartFile noticeFile = noticeDTO.getNoticeFile(); // 1.
            String originalFilename = noticeFile.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "C:/springboot_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
//            String savePath = "/Users/사용자이름/springboot_img/" + storedFileName; // C:/springboot_img/9802398403948_내사진.jpg
            noticeFile.transferTo(new File(savePath)); // 5.
            NoticeEntity noticeEntity = NoticeEntity.toSaveFileEntity(noticeDTO);
            Long savedNoticeId = noticeRepository.save(noticeEntity).getNoticeId();
            NoticeEntity notice = noticeRepository.findById(savedNoticeId).get();

            NoticeFileEntity noticeFileEntity = NoticeFileEntity.toNoticeFileEntity(notice, originalFilename, storedFileName);
            noticeFileRepository.save(noticeFileEntity);

        }


//        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
//        noticeRepository.save(noticeEntity);
    }

    @Transactional
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

    @Transactional
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
