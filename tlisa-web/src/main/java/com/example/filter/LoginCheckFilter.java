package com.example.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.pojo.Result;
import com.example.utils.JwtUtils;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse res=(HttpServletResponse) servletResponse;
        //1.获取请求的url
        String url = req.getRequestURL().toString();
        log.info("url:{}",url);

        //2.判断请求的url中是否包含login，如果包含说明是登录操作，放行
        if(url.contains("login")){
            log.info("登录操作，放行.......");
            filterChain.doFilter(servletRequest,servletResponse);
            return;//登录请求不执行放行后操作，因为没token
        }

        //3.获取请求头中的令牌（token）
        String jwt = req.getHeader("token");
        log.info("token:{}",jwt);

        //4.判断令牌是否为空，若为空，返回未登录
        if (!StringUtils.hasLength(jwt)) {//检查字符串是否既不为 null 也不为空串（即长度大于 0）
            log.info("请求头token为空，返回登录信息....");
            Result err = Result.error("NOT_LOGIN");
            //不在Controller中,需要手动将对象转化为Json ------>阿里巴巴fastJson
            String notLogin = JSONObject.toJSONString(err);//响应给浏览器的json字符串

            res.getWriter().write(notLogin);//响应给浏览器

            return;
        }

        //5.如果token存在，解析token。若解析token失败，返回错误结果（未登录）
        try {
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){//解析失败
            e.printStackTrace();
            log.info("解析令牌失败，返回错误的登录信息");
            Result err = Result.error("NOT_LOGIN");
            //不在Controller中,需要手动将对象转化为Json ------>阿里巴巴fastJson
            String notLogin = JSONObject.toJSONString(err);//响应给浏览器的json字符串

            res.getWriter().write(notLogin);//响应给浏览器

            return;
        }

        //6.放行
        log.info("令牌解析成功，放行......");
        filterChain.doFilter(servletRequest, servletResponse);

    }

}
