package com.rakshamitra.RakshaMitra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()  // Disable CSRF protection for simplicity (consider enabling it in production)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").authenticated()  // Require authentication for all admin routes
                .anyRequest().permitAll())  // Allow all other requests

            .formLogin(login -> login
                .loginPage("/admin/login")  // Custom login page
                .defaultSuccessUrl("/admin/admin_dashboard", true)  // Redirect to dashboard on successful login
                .failureUrl("/admin/login?error=true")  // Redirect back to login on failure
                .permitAll())  // Allow all users to access login page

            .logout(logout -> logout
                .logoutUrl("/admin/logout")  // Custom logout URL
                .logoutSuccessUrl("/admin/login?logout=true")  // Redirect to login after successful logout
                .invalidateHttpSession(true)  // Invalidate session on logout
                .clearAuthentication(true)  // Clear authentication
                .permitAll())  // Allow all users to access logout

            .sessionManagement(session -> session
                .invalidSessionUrl("/admin/login?sessionExpired=true")  // Redirect if session is invalid
                .maximumSessions(1)  // Allow only one active session per user
                .expiredUrl("/admin/login?expired=true"));  // Redirect if session expires
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for encoding
    }
}
