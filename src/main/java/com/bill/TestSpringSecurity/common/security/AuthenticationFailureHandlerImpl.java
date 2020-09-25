package com.bill.TestSpringSecurity.common.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(404);
        Map<String, String> result = new HashMap();
        result.put("message", "登入失敗");
        ObjectMapper objMap = new ObjectMapper();
        out.write(objMap.writeValueAsString(result));
        out.flush();
        out.close();
	}

}
