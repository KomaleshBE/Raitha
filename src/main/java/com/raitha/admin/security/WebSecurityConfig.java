package com.raitha.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig{

	@Bean
	public UserDetailsService userDetailsservice() {
		return new RaithaUserDetailsService();
		
	}
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	 return new BCryptPasswordEncoder();
	 }
	 
	 public DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		 authProvider.setUserDetailsService(userDetailsservice());
		 authProvider.setPasswordEncoder(passwordEncoder());
		 return authProvider;
	 }
	
	 
	@SuppressWarnings({ "removal", "deprecation" })
	@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
		{
			/*
			 * http.authorizeHttpRequests((authz)->authz .anyRequest().authenticated() )
			 * 
			 * .formLogin() .loginPage("/login") .usernameParameter("email") .permitAll()
			 * .and().logout().permitAll()
			 * .and().rememberMe().key("Abcdefghijklmnopqrs_1234567890")
			 * .tokenValiditySeconds(7 *24*60*60);
			 * 
			 * 
			 * return http.build();
			 */		        
		        http.authorizeRequests().requestMatchers("/login").permitAll()

                .requestMatchers("/users/**", "/settings/**").hasAuthority("Admin")

                .requestMatchers("Admin", "Editor", "Salesperson")

                .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")

                .anyRequest().authenticated()

                .and().formLogin()

                .loginPage("/login")

                    .usernameParameter("email")

                    .permitAll()

                .and()

                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
                .and()                

                .logout().permitAll();

        http.headers().frameOptions().sameOrigin();

        return http.build();


		        
		}
	
	

	@Bean
	 public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**","/webjars/**");
	    }
	
	@Bean
	  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
	    return authConfiguration.getAuthenticationManager();
	  }
}
