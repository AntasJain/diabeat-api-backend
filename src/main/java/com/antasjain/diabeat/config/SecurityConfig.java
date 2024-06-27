package com.antasjain.diabeat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @Bean
//    public UserDetailsManager userDetailsManager(){
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.setUsersByUsernameQuery("SELECT username, password, 1 as enabled FROM user WHERE username=?");
//        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT username, role FROM role WHERE username=?");
//
//        return userDetailsManager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(configurer -> configurer
//                .requestMatchers(HttpMethod.POST,"/login").permitAll()
//                .requestMatchers(HttpMethod.POST,"/user").permitAll()
//                // Secure recipe endpoints based on roles
//                .requestMatchers(HttpMethod.GET, "/recipes/**").hasAnyRole( "ADMIN","USER")
//                .requestMatchers(HttpMethod.POST, "/recipes").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/recipes/**").hasRole("ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/recipes/**").hasRole("ADMIN").anyRequest().authenticated());
//
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(csrf ->csrf.disable());
//        return http.build();
//    }
}
