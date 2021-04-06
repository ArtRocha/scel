package com.fatec.scel.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter{
	//aa
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
//		 http.cors();
		 http.authorizeRequests()
		 .antMatchers("/cliente/cadastrar").hasAnyRole("ADMIN", "BIB")
		 .antMatchers("/processo/cadastrar").hasAnyRole("ADMIN", "BIB")//
		 .antMatchers("/audiencia/cadastrar").hasAnyRole("ADMIN", "BIB")
		 .antMatchers("/advogado/cadastrar").hasRole("BIB") //somente login maria
		 .anyRequest().authenticated().and()
		 .formLogin().loginPage("/login").permitAll().and()
		 .logout().logoutSuccessUrl("/login?logout").permitAll();
	 }
	 @Override
	 public void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.inMemoryAuthentication()
		 .withUser("jose").password(pc().encode("123")).roles("ADMIN").and()
		 .withUser("maria").password(pc().encode("456")).roles("BIB");
	 }
	 
	 @Bean
	 public BCryptPasswordEncoder pc() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 @Override
	 public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/static/*", "/css/", "/js/", "/images/", "/h2-console/*");
	 } 
	
}