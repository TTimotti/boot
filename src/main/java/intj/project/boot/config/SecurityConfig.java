package intj.project.boot.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(i -> i.disable())
                .cors(i -> i.disable())
                .authorizeHttpRequests(request ->
                        request
                                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/assets/**", "/css/**", "/js/**", "/images/**", "/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(login ->
                        login
                                .loginPage("/index") // 커스텀 로그인 페이지 지정
                                .loginProcessingUrl("/login") // submit 받을 URL
                                .usernameParameter("userId") // username를 대체할 이름 설정
                                .defaultSuccessUrl("/", true) // 성공시 이동할 URL
                                .failureUrl("/user/signIn?ERR") // 추후에 핸들러로 모달
                                .permitAll()

                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")

                );

        return http.build();
    }

    //passwordEncoder
    @Bean
    BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}

