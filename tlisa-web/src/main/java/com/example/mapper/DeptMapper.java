package com.example.mapper;

import com.example.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from dept where id=#{id}")
    void deltetById(Integer id);

    /**
     * 添加部门
     * @param dept
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 按id查询部门
     * @param id
     */
    @Select("select * from dept where id=#{id}")
    Dept queryById(Integer id);

    /**
     * 根据id修改部门信息
     * @param deptTarget
     */
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    void updateById(Dept deptTarget);
}
