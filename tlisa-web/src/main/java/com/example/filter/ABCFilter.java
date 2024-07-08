package com.example.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
public class ABCFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("ABCFilter执行放行前操作.......");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("ABCFilter执行放行后操作.......");
    }
}
