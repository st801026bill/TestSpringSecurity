package com.bill.TestSpringSecurity.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
	private final String LOGGED_IN = "logged_in";
    private final String USER_TYPE = "user_type";
    
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String account = authentication.getName();
        Collection collection = authentication.getAuthorities();
        String authority = collection.iterator().next().toString();
        HttpSession session = request.getSession();
        session.setAttribute(LOGGED_IN, account);
        session.setAttribute(USER_TYPE, authority);
        Map<String, String> result = new HashMap<>();
        result.put("authority", authority);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        ObjectMapper om = new ObjectMapper();
        out.write(om.writeValueAsString(result));
        out.flush();
        out.close();
	}

}
