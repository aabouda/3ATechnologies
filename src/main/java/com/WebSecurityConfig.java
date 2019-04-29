package com;

 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.security.CustomUserDetailsService;

@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 @Autowired 
 private UserDetailsService userDetailsService;
 
 @Autowired
 private AuthenticationEntryPoint restAuthEntryPoint;
 
 @Autowired
 private AuthenticationSuccessHandler customSuccessHandler;
 
 @Autowired
 private AuthenticationFailureHandler customFailureHandler;
 
 @Autowired
 private AccessDeniedHandler customAccessDeniedHandler;
 
 @Autowired
 private LogoutSuccessHandler customLogoutSuccessHandler;
 
 @Autowired
 private AuthenticationProvider customAuthProvider;
 
 @Autowired
 DataSource dataSource; 
 

 @Override
 protected void configure(HttpSecurity http) throws Exception {	   
	 http
     .csrf().disable()
     .exceptionHandling()
     .accessDeniedHandler(customAccessDeniedHandler)
     .authenticationEntryPoint(restAuthEntryPoint)
     .and()
     .authorizeRequests()
     .antMatchers("/api/*").authenticated() //Regular expression of URLs that need auth
     .and()
     .formLogin()
     .loginProcessingUrl("/appLogin") //URL that user credentials should be sent to (POST request)
     .usernameParameter("username") //Name of login parameter when calling the login processing URL
     .passwordParameter("password") //Name of password parameter when calling the login processing URL
     .successHandler(customSuccessHandler)
     .failureHandler(customFailureHandler)
     .and()
     .logout()
     .logoutSuccessHandler(customLogoutSuccessHandler)
     .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); //URL called to kill session
	   
 
 }
 
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
     auth.authenticationProvider(customAuthProvider);
 }
 
}