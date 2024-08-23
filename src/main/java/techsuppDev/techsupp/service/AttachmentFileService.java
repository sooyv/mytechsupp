package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import techsuppDev.techsupp.DTO.NoticeDTO;
import techsuppDev.techsupp.DTO.ServiceDTO;
import techsuppDev.techsupp.domain.NoticeEntity;
import techsuppDev.techsupp.domain.NoticeFileEntity;
import techsuppDev.techsupp.repository.NoticeFileRepository;
import techsuppDev.techsupp.repository.NoticeRepository;
import techsuppDev.techsupp.repository.QuestionFileRepository;
import techsuppDev.techsupp.repository.QuestionRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttachmentFileService {
    private final NoticeRepository noticeRepository;
    private final NoticeFileRepository noticeFileRepository;
    private final QuestionRepository questionRepository;
    private final QuestionFileRepository questionFileRepository;
    @Value("${qnaServicePath}")
    String qnaServicePath;

    @Value("${noticeServicePath}")
    String noticeServicePath;


    /**
     * 파일 경로
     *
     * @param res
     * @param serviceDTO
     */
    public void filePath(HttpServletResponse res, ServiceDTO serviceDTO, String csType) throws FileNotFoundException {
        Path savePath;

        if (csType.equals("question")) {
            savePath = Paths.get(qnaServicePath + serviceDTO.getStoredFileName());
        } else if (csType.equals("notice")) {
            savePath = Paths.get(noticeServicePath + serviceDTO.getStoredFileName());
        } else {
            throw new IllegalArgumentException("Invalid csType: " + csType);
        }
        System.out.println("attachmentFileService filePath : "+ serviceDTO.getStoredFileName());

        //해당 경로에 파일이 없으면
        if (!savePath.toFile().exists()) {
            throw new FileNotFoundException("file not found at: " + savePath);
        }

        fileCopy(res, savePath);
    }


    /**
     * 파일 header 설정
     *
     * @param res
     * @param serviceDTO
     * @throws UnsupportedEncodingException
     */
    public void setFileHeader(HttpServletResponse res, ServiceDTO serviceDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode((String) serviceDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }


    /**
     * 파일 복사
     *
     * @param res
     * @param savePath
     */
    public void fileCopy(HttpServletResponse res, Path savePath) {
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(savePath.toFile());
            FileCopyUtils.copy(fis, res.getOutputStream());
            res.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 첨부 파일 수정 - 삭제
     */
    @Transactional
    public void deleteAttachFile(Long postId) {

        Optional<NoticeEntity> noticeEntityOptional = noticeRepository.findById(postId);

        if (noticeEntityOptional.isPresent()) {
            NoticeEntity noticeEntity = noticeEntityOptional.get();
            NoticeFileEntity noticeFile = noticeEntity.getNoticeFile();

            if (noticeFile != null) {
                noticeEntity.setNoticeFile(null);
                noticeFileRepository.delete(noticeFile);

                // 파일 시스템에서 파일 삭제
                File file = new File(noticeServicePath + noticeFile.getStoredFileName());
                if (file.exists()) {
                    file.delete();
                }
            }

        }
    }

}
