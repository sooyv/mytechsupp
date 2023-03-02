package techsuppDev.techsupp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.DispatcherType;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web -> )
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers().authenticated()
                .anyRequest().permitAll();
//                .antMatchers("/").hasAnyRole("ADMIN", "USER")

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("user/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .failureUrl("/login");

//        http.logout()
//                .

//        http.csrf().disable().cors().disable()
//            .authorizeHttpRequests(request -> request
//                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
//                    .requestMatchers().permitAll()
//
//                .anyRequest().authenticated()
//                        .and()
//                .formLogin("")
//                .loginPage("/user/login")
//                .defaultSuccessUrl("/")
//                .permitAll()
//            )
//                .logout(withDefaults());

//        http.authorizeHttpRequests().requestMatchers(
//                new AntPathRequestMatcher("/**")).permitAll();

//        http.cors().and();
//        http.csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
