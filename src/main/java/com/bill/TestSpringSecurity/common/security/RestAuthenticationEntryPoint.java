package com.bill.TestSpringSecurity.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		
        Map<String, String> result = new HashMap();
        result.put("error", "請先登入才能進行此操作");
        String error = mapper.writeValueAsString(result);
        
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(response.SC_UNAUTHORIZED);
        
        PrintWriter writer = response.getWriter();
        writer.write(error);
        writer.flush();
        writer.close();
	}
	
}
