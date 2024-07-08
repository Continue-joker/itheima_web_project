package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 测试请求参数接收
 */

//@RestController=@Controller+@ResponseBody
@RestController
public class RequestController {
    //    原始方式
//    @RequestMapping("/simplePara")
//    public String simplePara(HttpServletRequest request) {
//        // 获取请求参数
//        String name=request.getParameter("name");
//        String ageStr=request.getParameter("age");
//
//        int age=Integer.parseInt(ageStr);
//        System.out.println(name+":"+age);
//
//        return "OK";
//    }

    //    SpringBoot方式
    @RequestMapping("/simplePara")
    public String simplePara(String name, Integer age) {
        System.out.println(name + ":" + age);
        return "OK";
    }

    //    请求参数与形参不一致
    //    @RequestParam给指定参数绑定值
    @RequestMapping("/simpleParaNn")
    public String simpleParaNn(@RequestParam(name = "name", required = false) String username, Integer age) {
        System.out.println(username + ":" + age);
        return "OK";
    }

    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "ok";
    }

    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "ok";
    }

    @RequestMapping("/arrayParam")
    public String arrayParam(String[] hobby) {
        System.out.println(Arrays.toString(hobby));
        return "ok";
    }

    @RequestMapping("/listParam")
    public String listParam(@RequestParam List<String> hobby) {
        System.out.println(hobby);
        return "ok";
    }

    @RequestMapping("/dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime) {
        System.out.println(updateTime);
        return "ok";
    }

    //    @RequestBody将json封装为user对象
    //    要求Json中的key与对象中的属性名一致
    @RequestMapping("/jsonParam")
    public String jsonParam(@RequestBody User user) {
        System.out.println(user);
        return "ok";
    }

    //    @PathVariable将接收到的请求参数绑定到形参id

    @RequestMapping("/path/{id}")
    public String jsonParam(@PathVariable Integer id) {
        System.out.println(id);
        return "ok";
    }

    @RequestMapping("/a/{id}/{name}")
    public String jsonParam(@PathVariable Integer id, @PathVariable String name) {
        System.out.println(id + " " + name);
        return "ok";
    }


}
