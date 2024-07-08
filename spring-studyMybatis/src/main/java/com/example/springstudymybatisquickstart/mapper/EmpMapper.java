package com.example.springstudymybatisquickstart.mapper;

import com.example.springstudymybatisquickstart.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Delete("delete from tb_emp where id=#{id}")
    //public void delete(Integer id);

    public int delete(Integer id);
}
