package techsuppDev.techsupp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return ((web) -> web.ignoring().antMatchers("/style/**", "/script/**", "/bootstrap/**"));
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/user/**").authenticated()
                .antMatchers("/admin/**").authenticated()
                .antMatchers("/invest/**").authenticated()
                .antMatchers("/feedbackSelect/feedback/form/**").authenticated()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**").hasRole("ADMIN")          // ROLE_ADMIN 권한 유저 접근가능
                .anyRequest().permitAll();

        http.exceptionHandling().accessDeniedPage("/access/denied");        // Access Denied Page

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/member/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/member/loginsuccess", true)
                .failureUrl("/login")


                .and()
            .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID");       // 로그아웃 후 쿠키 삭제

        // 세션
        http.sessionManagement()
                .sessionFixation().migrateSession()
                .maximumSessions(1) // 최대 세션 수
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/");

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
