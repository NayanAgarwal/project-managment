package com.jrp.pma.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// use in-memory hardcoded password
		// with only the given username,password,role
		/*
		 * auth.inMemoryAuthentication().withUser("myuser").password("Atlas@123").roles(
		 * "USER").and().withUser("admin") .password("Atlas@123").roles("Admin");
		 */
		
		
		//use JDBC with the H2-database
		/*
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .withDefaultSchema().withUser("myuser").password("Atlas@123").roles("USER")
		 * .and() .withUser("admin").password("Atlas@123").roles("ADMIN");
		 */
		
		//use real time database such as postgres with actual tables to query from using JDBC authentication

		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enabled "+
								"from user_accounts where username=?" )
		.authoritiesByUsernameQuery("select username,role "+
								"from user_accounts where username=?")
		.passwordEncoder(bCryptEncoder);
	}

	

	
	  @Override 
	  protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  .antMatchers("/projects/new").hasRole("ADMIN")
	  .antMatchers("/projects/save").hasRole("ADMIN")
	  .antMatchers("/employees/new").hasRole("ADMIN")
	  .antMatchers("/employees/save").hasRole("ADMIN")
				/* .antMatchers("/h2-console/**").permitAll() */
	  .antMatchers("/","/**").permitAll()
	  .and()
	  .formLogin(); 
	  
		
		/*
		 * http.csrf().disable(); http.headers().frameOptions().disable();
		 */
		 
	  }
	 

}
