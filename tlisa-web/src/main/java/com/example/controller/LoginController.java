package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.Result;
import com.example.service.EmpService;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}",emp);
        Emp e= empService.login(emp);
        //登陆成功，生成令牌，下发令牌
        if(e!=null){
            Map<String, Object> map=new HashMap<>();
            map.put("id",e.getId());
            map.put("name",e.getName());
            map.put("username",e.getUsername());

            String jwt= JwtUtils.generateJwt(map);
            return Result.success(jwt);
        }
        //登录失败返回错误信息

//        return e==null?Result.error("用户名或密码错误"):Result.success(e);
        return Result.error("用户名或密码错误!!!");
    }
}
