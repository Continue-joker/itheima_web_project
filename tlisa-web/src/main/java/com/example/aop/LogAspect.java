package com.example.aop;

import com.alibaba.fastjson.JSONObject;
import com.example.mapper.OperateLogMapper;
import com.example.pojo.OperateLog;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect//切面类
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知，记录系统操作日志
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("@annotation(com.example.anno.Log)")
    public Object recordLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //1.操作员id---当前登录的员工id（从令牌中获取）
        //获取请求头中jwt令牌
        String token = request.getHeader("token");
        //解析令牌
        Claims claims = JwtUtils.parseJWT(token);
        //从token中获取员工id
        Integer operateUser = (Integer) claims.get("id");

        //2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //3.操作类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //4.操作方法
        String methedName = proceedingJoinPoint.getSignature().getName();
        //5.操作方法参数
        Object[]args=proceedingJoinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录原始方法运行开始时间
        long begin = System.currentTimeMillis();
        //调用原始方法，并运行
        Object result = proceedingJoinPoint.proceed();
        //记录原始方法运行的结束时间
        long end = System.currentTimeMillis();

        //6.方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //7.操作耗时
        long costTime = end - begin;

        //记录操作日志
        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methedName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);

        return result;
//        return null;//可覆盖原始方法返回数据
    }
}
