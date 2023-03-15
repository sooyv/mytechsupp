package techsuppDev.techsupp.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class Mailconfig {

    @Bean
    public JavaMailSender javaMailService() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.naver.com");                   // smtp 서버 주소
        javaMailSender.setUsername("techsupp@naver.com");           // naver 아이디
        javaMailSender.setPassword("kitri0321!");                   // naver 비밀번호

        javaMailSender.setPort(465);                                // 메일 인증 서버 포트

        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();

        properties.setProperty("mail.transport.protocol", "smtp");      // 프로토콜 설정
        properties.setProperty("mail.smtp.auth", "true");               // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true");    // smtp strattles 사용
        properties.setProperty("mail.debug", "true");                   // 디버그
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com"); // ssl 인증 서버는 smtp.naver.com
        properties.setProperty("mail.smtp.ssl.enable","true");          // ssl 사용
        return properties;

    }
}

