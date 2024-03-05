package com.vs45tech.com.restfulwebservice.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

//import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,HandlerMappingIntrospector introspector) throws Exception{
return http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource( request -> {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowedHeaders(List.of("*"));
      return configuration;}))
  .authorizeHttpRequests(auth -> auth
  .requestMatchers("/authenticate").permitAll()
 /*  .requestMatchers(PathRequest.toH2Console()).permitAll()  */// h2-console is a servlet and NOT recommended for a production
  .requestMatchers(HttpMethod.OPTIONS,"/**")
  .permitAll()
  .anyRequest()
  .authenticated())
.csrf(AbstractHttpConfigurer::disable)
.sessionManagement(session -> session.
  sessionCreationPolicy(SessionCreationPolicy.STATELESS))
.oauth2ResourceServer(
       OAuth2ResourceServerConfigurer ->
          OAuth2ResourceServerConfigurer
                  .jwt(Customizer.withDefaults())
          )
.httpBasic(
  Customizer.withDefaults()) 
.headers(header -> {header.
  frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()) ;})
.build(); 
    }
}
