package techsuppDev.techsupp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        System.out.println("session입ㄴ다: " + session);

        if (session != null) {          // 세션이 있는 경우
            response.sendRedirect("/");         // "/"로 이동
        } else {
            response.sendRedirect("/login");    // "/login"으로 이동
        }
    }
}
