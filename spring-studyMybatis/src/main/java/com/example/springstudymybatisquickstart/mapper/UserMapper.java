package com.example.springstudymybatisquickstart.mapper;

import com.example.springstudymybatisquickstart.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper//在运行时，会自动生成该接口的实现类对象（代理对象），并将该对象交给IOC容器管理
public interface UserMapper {
    //查询全部用户信息
    @Select("Select * from user")
    public List<User> list();
}
