package com.example.demo.securingweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
  
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authorizeRequests ->
        authorizeRequests
          .requestMatchers("/admin/signin","/admin/signup").permitAll()
          .anyRequest().authenticated()
      )
      .formLogin(formLogin ->
        formLogin
          .loginPage("/admin/signin")
          .usernameParameter("email")
          .passwordParameter("password")
          .successForwardUrl("/admin/contacts")
          .permitAll()
      )
      .logout(logout ->
        logout
          .logoutRequestMatcher(new AntPathRequestMatcher("/admin/signout"))
      );
    
    return http.build();
  }

}