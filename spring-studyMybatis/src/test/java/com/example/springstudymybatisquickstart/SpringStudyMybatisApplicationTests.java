package com.example.springstudymybatisquickstart;

import com.example.springstudymybatisquickstart.mapper.EmpMapper;
import com.example.springstudymybatisquickstart.mapper.UserMapper;
import com.example.springstudymybatisquickstart.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringStudyMybatisApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testListUser(){
        List<User> userList = userMapper.list();
        userList.stream().forEach(user -> {
            System.out.println(user);
        });
    }
    @Autowired
    private EmpMapper empMapper;
    @Test
    public void testDelete(){
        int delete = empMapper.delete(16);
        System.out.println(delete);
    }


}
