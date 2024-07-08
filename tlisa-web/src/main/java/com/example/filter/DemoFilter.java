package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * WebFilter入门程序
 */
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override//初始化，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);//调用接口的默认方法
        System.out.println("初始化WebFilter");
    }

    @Override//拦截请求之后调用，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("WebFilter拦截请求");

        System.out.println("执行放行前操作.......");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("执行放行后操作.......");
    }

    @Override//销毁方法，只调用一次
    public void destroy() {
        //Filter.super.destroy();
        System.out.println("销毁WebFilter");
    }//注意Filter的包
}
