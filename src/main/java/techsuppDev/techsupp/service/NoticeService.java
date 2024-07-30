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

    @Value("${serviceSavePath}")
    String fileUploadPath;


    // 공지사항 작성
    public void noticeResister(NoticeDTO noticeDTO) throws IOException {
        System.out.println("saveNotice 확인 :" + noticeDTO);
        if (noticeDTO.getNoticeFile() == null || noticeDTO.getNoticeFile().isEmpty()) {
            // 첨부 파일 없음.
            NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
            noticeRepository.save(noticeEntity);
        } else {
            // 첨부 파일 있음.
            saveNoticeFile(noticeDTO);
//          for(MultipartFile noticeFile: noticeDTO.getNoticeFile()) {
        }
    }


    // 첨부파일 저장 로직
    public NoticeEntity saveNoticeFile(NoticeDTO noticeDTO) throws IOException {

        MultipartFile noticeFile = noticeDTO.getNoticeFile(); // 1.
        String originalFilename = noticeFile.getOriginalFilename(); // 2.
        String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
        String savePath = fileUploadPath + storedFileName;
        noticeFile.transferTo(new File(savePath)); // 5.

        // 첨부 파일이 있을때 toSaveFileEntity로 첨부파일 추가 noticeEntity 변환
        NoticeEntity noticeEntity = NoticeEntity.toSaveFileEntity(noticeDTO);
        noticeRepository.save(noticeEntity);         // 저장

        NoticeFileEntity noticeFileEntity = NoticeFileEntity.toNoticeFileEntity(noticeEntity, originalFilename, storedFileName);
        noticeFileRepository.save(noticeFileEntity);

        return noticeFileEntity.getNotice();
    }

    // 공지사항 수정
    @Transactional
    public NoticeEntity noticeUpdate(NoticeDTO noticeDTO) throws IOException {
        NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
        MultipartFile noticeFile = noticeDTO.getNoticeFile();

        // 기존 파일 삭제 및 새 파일 저장 로직
        if (noticeFile != null && !noticeFile.isEmpty()) {
            deleteNoticeFile(noticeDTO.getNoticeId()); // 기존 첨부 파일 삭제
            String storedFileName = saveFile(noticeFile); // 새로운 파일 저장
            noticeEntity.setFileAttached(1);
            updateNoticeFileEntity(noticeEntity, noticeFile.getOriginalFilename(), storedFileName);

        } else if (noticeDTO.getOriginalFileName() != null) {
            // 기존 파일 유지
            noticeEntity.setFileAttached(1);
        } else {
            // 첨부파일 없음
            noticeEntity.setFileAttached(0);
        }

        return noticeRepository.save(noticeEntity);
    }

    // 파일 저장
    private String saveFile(MultipartFile file) throws IOException {
        System.out.println("saveFile");
        String originalFilename = file.getOriginalFilename();
        String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
        String savePath = fileUploadPath + storedFileName;
        file.transferTo(new File(savePath));
        return storedFileName;
    }


    // NoticeFileEntity를 업데이트하거나 새로 생성하여 NoticeEntity에 연결
    private void updateNoticeFileEntity(NoticeEntity noticeEntity, String originalFileName, String storedFileName) {
        NoticeFileEntity noticeFileEntity = noticeEntity.getNoticeFile();
        if (noticeFileEntity == null) {
            noticeFileEntity = new NoticeFileEntity();
            noticeFileEntity.setNotice(noticeEntity);
        }
        noticeFileEntity.setOriginalFileName(originalFileName);
        noticeFileEntity.setStoredFileName(storedFileName);
        noticeFileRepository.save(noticeFileEntity);
    }


    // 공지사항 삭제
    @Transactional
    public Long deleteNotice(Long noticeId) {
        noticeRepository.deleteByNotionId(noticeId);
        return noticeId;
    }

    // 공지사항 첨부파일 삭제
    @Transactional
    public void deleteNoticeFile(Long noticeId) {
        Optional<NoticeEntity> noticeEntityOptional = noticeRepository.findById(noticeId);

        if (noticeEntityOptional.isPresent()) {
            NoticeEntity noticeEntity = noticeEntityOptional.get();
            NoticeFileEntity noticeFile = noticeEntity.getNoticeFile();

            if (noticeFile != null) {
                noticeEntity.setNoticeFile(null);
                noticeFileRepository.delete(noticeFile);

                // 파일 시스템에서 파일 삭제
                File file = new File(fileUploadPath + noticeFile.getStoredFileName());
                if (file.exists()) {
                    file.delete();
                }
            }

        }
    }


    @Transactional
    public List<NoticeDTO> findAllNotice() {

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
            System.out.println("noticeEntity hits 확인: "+ noticeEntity.getNoticeHits());
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;
        } else {
            return null;
        }
    }

    public Page<NoticeDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 10; // 한 페이지에 보여줄 글 갯수
        // 힌페이지당 3개씩 글을 보여주고 정렬 기준은 noticeId 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0 부터 시작
        Page<NoticeEntity> noticeEntities =
                noticeRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "noticeId")));
        System.out.println("noticeEntities.getContent() = " + noticeEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("noticeEntities.getTotalElements() = " + noticeEntities.getTotalElements()); // 전체 글갯수
        System.out.println("noticeEntities.getNumber() = " + noticeEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("noticeEntities.getTotalPages() = " + noticeEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("noticeEntities.getSize() = " + noticeEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("noticeEntities.hasPrevious() = " + noticeEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("noticeEntities.isFirst() = " + noticeEntities.isFirst()); // 첫 페이지 여부
        System.out.println("noticeEntities.isLast() = " + noticeEntities.isLast()); // 마지막 페이지 여부

        // 목록: noticeid, writer, title, hits,
        Page<NoticeDTO> noticeDTOS = noticeEntities.map(notice -> new NoticeDTO(notice.getNoticeId(),
                notice.getNoticeWriter(), notice.getNoticeTitle(), notice.getNoticeHits()));
        return noticeDTOS;
    }


}















