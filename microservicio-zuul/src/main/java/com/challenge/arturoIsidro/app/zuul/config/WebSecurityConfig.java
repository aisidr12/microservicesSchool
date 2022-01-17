//package com.challenge.arturoIsidro.app.zuul.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//		 http.authorizeRequests().antMatchers("/actuator/**").permitAll()
//		 .anyRequest().authenticated().and().build();
//	    }
//	
////    @Bean
////    public SecurityWebFilterChain securitygWebFilterChain(
////      ServerHttpSecurity http) {
////
////            return http.authorizeExchange()
////                    .pathMatchers("/actuator/**").permitAll()
////                    .anyExchange().authenticated()
////                    .and().build();
////    }
//}
