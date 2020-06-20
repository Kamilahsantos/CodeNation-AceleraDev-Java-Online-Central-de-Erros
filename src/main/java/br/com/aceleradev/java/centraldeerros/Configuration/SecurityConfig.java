package br.com.aceleradev.java.centraldeerros.Configuration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import br.com.aceleradev.java.centraldeerros.Auth.JWTAuth;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public CorsConfigurationSource corsConfigurationSource(){
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Access-Control-Allow-Headers", "Authorization","X-Requested-With"));
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return  source;
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2/", "/h2/**", "/h2-console/**", "/h2/h2-console/**", "/v2/api-docs",
      "/configuration/ui", "/swagger-resources/**", "/configuration/**", "/swagger-ui.html", "/webjars/**");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
      .addFilterAfter(new JWTAuth(), UsernamePasswordAuthenticationFilter.class)
      .authorizeRequests().antMatchers(HttpMethod.POST, "/api/login", "/api/account").permitAll().anyRequest()
      .authenticated();
  }




}
