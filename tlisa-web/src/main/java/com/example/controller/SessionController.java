package com.example.controller;

import com.example.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Cookie,HtttpSession演示
 */
@Slf4j
@RestController
public class SessionController {

    /**
     * 设置cookie---服务器给浏览器相应cookoie
     *
     * @param response
     * @return
     */
    @GetMapping("c1")
    public Result cookie1(HttpServletResponse response) {
        log.info("设置cookie:{}", response);
        response.addCookie(new Cookie("username", "helloword!"));
        return Result.success();
    }

    /**
     * 请求cookie,服务器从浏览器请求的http请求头中获取cookie
     *
     * @param request
     * @return
     */
    @GetMapping("c2")
    public Result cookie2(HttpServletRequest request) {
        log.info("请求cookie:{}", request);
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("username")){
                System.out.println("username:"+cookie.getValue());
            }
        }
        return Result.success();
    }

    /**
     * 往HttpSession中存储值
     * @param session
     * @return
     */
    @GetMapping("s1")
    public Result session1(HttpSession session){
        log.info("HttpSession-c1:{}",session.hashCode());
        session.setAttribute("logUser","Tom");

        return Result.success();
    }

    /**
     * 从HttpSession中获取值
     * @param request
     * @return
     */
    @GetMapping("s2")
    public Result session2(HttpServletRequest request){
        HttpSession session=request.getSession();
        log.info("HttpSession-c2:{}",session.hashCode());

        Object logUser=session.getAttribute("logUser");
        log.info("logUser - {}",logUser);
        return Result.success();
    }
}
