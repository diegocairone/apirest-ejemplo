package com.eiva.propuesta01;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.eiva.propuesta01.api.auth.HttpAuthenticationEntryPoint;
import com.eiva.propuesta01.api.auth.HttpAuthenticationFailureHandler;
import com.eiva.propuesta01.api.auth.HttpAuthenticationSuccessHandler;
import com.eiva.propuesta01.api.auth.HttpLogoutSuccessHandler;

@Configuration @EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private HttpAuthenticationEntryPoint httpAuthenticationEntryPoint = null;
	@Autowired private HttpAuthenticationSuccessHandler httpAuthenticationSuccessHandler = null;
	@Autowired private HttpAuthenticationFailureHandler httpAuthenticationFailureHandler = null;
	@Autowired private HttpLogoutSuccessHandler httpLogoutSuccessHandler = null;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	    http.authorizeRequests()
		    	.anyRequest()
		    	.fullyAuthenticated()
		    	.and()
		    .httpBasic()
		    	.authenticationEntryPoint(httpAuthenticationEntryPoint)
	    		.and()
	    	.csrf().disable()
		    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		    	.and()
	    	.formLogin()
	    		.permitAll()
	    		.successHandler(httpAuthenticationSuccessHandler)
	    		.failureHandler(httpAuthenticationFailureHandler)
	    		.and()
	    	.logout()
	    		.logoutRequestMatcher(new AntPathRequestMatcher("/api/logout"))
	    		.logoutSuccessHandler(httpLogoutSuccessHandler);
	  }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        	.withUser("admin").password("qwerty").roles("ADMINISTRADOR", "USUARIO").and()
        	.withUser("usuario").password("asdfgh").roles("USUARIO");
    }
}
