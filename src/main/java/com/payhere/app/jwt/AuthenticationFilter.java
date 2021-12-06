package com.payhere.app.jwt;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        TokenProvider tokenProvider = new TokenProvider();
        String token = request.getHeader("Authentication");
        //tokenProvider.validateToken(token); 오류발생

        chain.doFilter(request,response);
    }
}
