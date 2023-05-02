package com.ra.busBooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.ra.busBooking.service.DefaultUserServiceImpl;


@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DefaultUserServiceImpl customUserDetailsService;
	    
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws 
                                                Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/registration","/login").permitAll();

    }
}
