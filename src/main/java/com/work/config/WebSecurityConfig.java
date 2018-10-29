package com.work.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests().anyRequest().authenticated().and().httpBasic().and()
            .cors().and().csrf().disable().headers().disable();
    }
    /*
     * 
     * STALNO VRACA SQLFEATURENOTSUPPORTEDEXCEPTION, 
     * nije mi jasno sta od ovoga tacno ne funkcionise sa VoltDB
    @SuppressWarnings("deprecation")
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username,password, 1 from users where username=?")
        .authoritiesByUsernameQuery("select username, authority from authorities where username=?")
        .passwordEncoder(new MessageDigestPasswordEncoder("SHA-256"));
    }
    
    
    
    @Bean
    public JdbcTemplate jdbcTemplate() {
    	final JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	jdbcTemplate.setDataSource(dataSource);
    	jdbcTemplate.afterPropertiesSet();
    	
    	return jdbcTemplate;
    }
    
    
    */
   

}