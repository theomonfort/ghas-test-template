package com.octodemo.vuln;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.web.SecurityFilterChain;

// CWE-352: Spring Security CSRF protection disabled.
// Rule: java/spring-disabled-csrf-protection
// Autofix: typically NOT provided — re-enabling CSRF often breaks existing
// non-browser clients; fix needs coordinated client + server changes.
@Configuration
@EnableWebSecurity
public class SpringDisabledCsrfConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .httpBasic();
    return http.build();
  }
}
