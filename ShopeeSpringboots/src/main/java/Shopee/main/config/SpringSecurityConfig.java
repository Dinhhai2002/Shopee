package Shopee.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@ConfigurationProperties("security")
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages= {
		"Shopee.main.repository","Shopee.main.Service"})

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired 
	private loginSuccessHandler successHandler;
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(successHandler);
		
		//http.formLogin().defaultSuccessUrl("/home").failureForwardUrl("/login?error");
		
		http.logout().logoutSuccessUrl("/login");
		
		http.exceptionHandling().accessDeniedPage("/login?accessDenied");
		
		http.authorizeRequests().antMatchers("/").permitAll()
		.antMatchers("/admin/**").hasAuthority("Role_admin")
		.antMatchers("/managerProduct/**").hasAnyAuthority("Role_seller")
		.antMatchers("/shipper/**").hasAnyAuthority("Role_shipper");
		
		//.antMatchers("/admin/**").access("hasRole('Role_seller')");
		http.csrf().disable();
		
		
		
		
	}
	
}
