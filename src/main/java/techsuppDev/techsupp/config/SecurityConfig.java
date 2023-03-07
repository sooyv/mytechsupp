package techsuppDev.techsupp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.persistence.PersistenceContext;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> )
//    }
    @Autowired
    private UserDetailsimplService userDetailsimplService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return ((web) -> web.ignoring().antMatchers("/style/**", "/script/**", "/bootstrap/**"));
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
//                .antMatchers("/user/**").authenticated()            // 스프링 시큐리티에 의해 로그인이 되면 접근가능
                .antMatchers("/checkPassword").authenticated()
//                .antMatchers("/mypage").authenticated()
                .antMatchers("/edituser").authenticated()
//                .antMatchers("/admin/**").authenticated()
//                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/admin/**").hasRole("ADMIN")              // ROLE_ADMIN 권한 유저 접근가능
                .anyRequest().permitAll();

        http.exceptionHandling().accessDeniedPage("/access/denied");        // Access Denied Page

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/member/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/member/logout")
                .logoutSuccessUrl("/");

        // 세션
        http.sessionManagement()
//                .invalidSessionUrl("?")
                .maximumSessions(1) // 최대 세션 수
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/");

        return http.build();
    }



    // 세션
//    @Bean
//    public HttpSessionEventPublisher httpSessionEventPublisher() {
//        return new HttpSessionEventPublisher();
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
