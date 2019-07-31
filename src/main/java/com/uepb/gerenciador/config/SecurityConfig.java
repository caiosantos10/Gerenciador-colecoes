package com.uepb.gerenciador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
 	protected void configure(HttpSecurity http) throws Exception {

		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		  
		http.authorizeRequests()                                                                
				.antMatchers( 
						"/h2-console/**",
						"/static/**",
						"/usuario/**",
						"/adduser/**"
						).permitAll() 	                 
				.antMatchers(
						"/amigo/**"
						).authenticated()                                  
				.anyRequest().authenticated()                                                   
         	.and()
         		.formLogin()
         		.failureUrl("/login?error=true")
         		.loginPage("/login")
         		.defaultSuccessUrl("/home")
         		.permitAll()
         	.and()
         	.logout()
         	.logoutSuccessUrl("/login")
         	.permitAll();
		
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
 	
 	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
 	
}
