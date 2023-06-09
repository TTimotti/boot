package intj.project.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
//                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/assets/**", "/css/**", "/scripts/**", "/*", "/user/*").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login") // 커스텀 로그인 페이지 지정
                        .loginProcessingUrl("/loginProcess") // submit 받을 URL
                        .usernameParameter("userId") // username를 대체할 이름 설정
                        .passwordParameter("password")
                        .defaultSuccessUrl("/index", true) // 성공시 이동할 URL
                        .failureUrl("/index?foo") // 추후에 핸들러로 모달
                        .permitAll()

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()

                );

        return http.build();
    }

    //passwordEncoder
    @Bean
    BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}

