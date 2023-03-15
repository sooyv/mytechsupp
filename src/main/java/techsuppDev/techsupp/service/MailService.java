package techsuppDev.techsupp.service;

import com.nimbusds.oauth2.sdk.auth.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

// 이메일 인증 구현 MailService
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public String code;

    // 랜덤 인증 코드
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    // 메일 내용 작성
    private MimeMessage createMessage(String email) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);         // 보내는 대상
        message.setSubject("TECHSUPP 회원가입 이메일 인증");                  // 제목

        // 회원가입 메일 인증
        String msg = "";
        msg += "<h1>TECHSUPP 이메일 인증번호입니다.</h1>";
        msg += "<div style='font-size:130%'>";
        msg += "인증 코드 : <strong>";
        msg += code + "</strong><div><br/>";
        msg += "</div>";
        message.setText(msg, "utf-8", "html");

        message.setFrom(new InternetAddress("techsupp@naver.com"));

        return message;
    }

    // 회원가입 인증 메일 발송
    public String sendMail(String email) throws Exception {
        code = createKey();
        MimeMessage mimeMessage = createMessage(email);

        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException mailException) {
            mailException.printStackTrace();
            throw new IllegalStateException();
        }
        return code;
    }



    // 비밀번호 재발급
    private MimeMessage createPwMessage(String email) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);         // 보내는 대상
        message.setSubject("TECHSUPP 비밀번호 재발급");                  // 제목

        // 비밀번호 재발급
        String pwmsg = "";
        pwmsg += "<h1>TECHSUPP 임시비밀번호 발급.</h1>";
        pwmsg += "<div style='font-size:130%'>";
        pwmsg += "임시비밀번호 : <strong>";
        pwmsg += code + "</strong><div><br/>";
        pwmsg += "</div>";
        message.setText(pwmsg, "utf-8", "html");

        message.setFrom(new InternetAddress("techsupp@naver.com"));

        return message;
    }

    // 비밀번호 재발급 메일 발송
    public String sendPwMail(String email) throws Exception {
        code = createKey();
        MimeMessage mimeMessage = createPwMessage(email);

        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException mailException) {
            mailException.printStackTrace();
            throw new IllegalStateException();
        }
        return code;
    }








}
