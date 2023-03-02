package com.niit.PizzaService.filter;

import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {
        //typecast servletRequest /response to Httpservlet request/response
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println("Authheader = "+ authHeader);
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        if(authHeader==null || !authHeader.startsWith("Bearer")) {
            //token is missing or invalid token
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            servletOutputStream.print("missing or invalid token");
            servletOutputStream.close();
        }
        else {
            //token is valid
            String jwttoken = authHeader.substring(6);
            System.out.println("jwttoken "+jwttoken);
            String username = Jwts.parser().setSigningKey("securityKey").parseClaimsJws(jwttoken).getBody().getSubject();
            httpServletRequest.setAttribute("firstName", username);
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
