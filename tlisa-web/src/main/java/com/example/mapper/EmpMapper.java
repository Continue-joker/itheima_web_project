package com.example.mapper;

import com.example.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 学生管理
 */
@Mapper
public interface EmpMapper {
//    /**
////     * 查询总记录数
////     * @return
////     */
////    @Select("select count(*) from emp")
////    public Long count();
////
////    /**
////     * 分页查询
////     * @param start
////     * @param pageSize
////     * @return
////     */
////    @Select("select * from emp limit #{start},#{pageSize}")
////    public List<Emp> page(@Param("start") Long start, @Param("pageSize") Long pageSize);

    /**
     * 查询所有员工信息
     * @return
     */
//    @Select("select * from emp")
    public  List<Emp> list(@Param("name") String name,@Param("gender") Short gender, String image,@Param("begin") LocalDate begin,@Param("end") LocalDate end);

    /**
     * 批量删除员工
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time)"+
            "values (#{username},#{name},#{gender},#{image},#{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByUsernameAndPassword(Emp emp);

    @Delete("delete from emp where dept_id=#{id}")
    void delteteByDeptId(Integer id);
}
