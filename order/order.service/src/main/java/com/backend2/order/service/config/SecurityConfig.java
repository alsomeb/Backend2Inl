package com.backend2.order.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@EnableRetry // this app has retry logic
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
                http.authorizeHttpRequests((requests) -> requests
                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                        DispatcherType.ERROR).permitAll()
                        .requestMatchers("/orders").hasRole("USER")
                        .requestMatchers( "/**").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults())
                .csrf().disable(); // Så vi kan göra mer än GET req
        
                http.httpBasic(Customizer.withDefaults()); // Så vi kan anv 'Authorization' Header för Postman
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$kxYrWYRtvdTfqAni9qKySubVSkXWQtDbyoct1Yuo6RWUmq0U0qQBe")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$10$kxYrWYRtvdTfqAni9qKySubVSkXWQtDbyoct1Yuo6RWUmq0U0qQBe")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}