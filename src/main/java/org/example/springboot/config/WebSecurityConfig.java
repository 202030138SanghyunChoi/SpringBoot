package org.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"), new AntPathRequestMatcher("/static/**/**")));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests((request) -> {
            request.requestMatchers(antMatcher("/login")).permitAll();
            request.requestMatchers(antMatcher("/signup")).permitAll();
            request.requestMatchers(antMatcher("/user")).permitAll();

            request.anyRequest().authenticated();
        });

        http.formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/articles", true).permitAll());

        http.logout(logout -> logout.logoutSuccessUrl("/login").invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}