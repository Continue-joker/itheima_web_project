package com.example.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标识AOP切入点
 */
@Retention(RetentionPolicy.RUNTIME)//允许在运行时检查和处理这些注解
@Target(ElementType.METHOD)//注解作用于方法上
public @interface Log {
}
