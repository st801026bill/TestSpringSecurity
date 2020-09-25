package com.bill.TestSpringSecurity.common.security;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

	 @Bean
     LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
         LoginAuthenticationFilter filter = new LoginAuthenticationFilter();
         filter.setAuthenticationManager(authenticationManagerBean());
         filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandlerImpl());
         filter.setAuthenticationFailureHandler(new AuthenticationFailureHandlerImpl());
         filter.setFilterProcessesUrl("/api/login");
         return filter;
	 }
	 
	 @Override
     @Bean
     public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
     }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.inMemoryAuthentication()
	        .withUser("admin").password("123456").roles("ADMIN").and()
	        .withUser("user").password("123456").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.exceptionHandling()
	        .authenticationEntryPoint(new RestAuthenticationEntryPoint())
	        .accessDeniedHandler(new AccessDeniedHandlerImpl())
        .and()
	        .addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .authorizeRequests()
	        .antMatchers("/api/admin/**").hasRole("ADMIN")
	        .antMatchers("/api/user/**").hasRole("USER")
        .and()
	        .logout()
	        .logoutUrl("/api/logout")
	        .invalidateHttpSession(true)
	        .logoutSuccessHandler((req, resp, auth) -> {
	            resp.setContentType("application/json;charset=UTF-8");
	            PrintWriter out = resp.getWriter();
	            resp.setStatus(200);
	            Map<String, String> result = new HashMap();
	            result.put("message", "登出成功");
	            ObjectMapper om = new ObjectMapper();
	            out.write(om.writeValueAsString(result));
	            out.flush();
	            out.close();
	        })
        .and()
        .csrf()
        .disable();
	}
	
}
