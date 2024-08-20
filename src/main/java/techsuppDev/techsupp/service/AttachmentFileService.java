package techsuppDev.techsupp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import techsuppDev.techsupp.DTO.NoticeDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class AttachmentFileService {
    @Value("${qnaServicePath}")
    String qnaServicePath;

    @Value("${noticeServicePath}")
    String noticeServicePath;

    /**
     * 파일 header 설정
     *
     * @param res
     * @param noticeDTO
     * @throws UnsupportedEncodingException
     */
    private void setFileHeader(HttpServletResponse res, NoticeDTO noticeDTO) throws UnsupportedEncodingException {
        res.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode((String) noticeDTO.getOriginalFileName(), "UTF-8"));
        res.setHeader("Content-Transfer-Encoding", "binary");
        res.setHeader("Content-Type", "application/download; utf-8");
        res.setHeader("Pragma", "no-cache;");
        res.setHeader("Expires", "-1;");
    }


}
