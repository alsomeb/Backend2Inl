package com.backend2.order.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;





@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                .dispatcherTypeMatchers(DispatcherType.FORWARD,
                        DispatcherType.ERROR).permitAll()
                        .requestMatchers("/orders").hasRole("USER")
                        .requestMatchers( "/**").hasRole("ADMIN"))
                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$38uf6UjXN0ReHBg/UECzVOMftm0n7GYm4ixFH8Oh3xgtz.chbzYPa")
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$10$38uf6UjXN0ReHBg/UECzVOMftm0n7GYm4ixFH8Oh3xgtz.chbzYPa")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }


}