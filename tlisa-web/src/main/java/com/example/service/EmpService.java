package com.example.service;

import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理服务
 */
public interface EmpService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
   PageBean page(Integer page, Integer pageSize,String name, Short gender,String image,
                 LocalDate begin, LocalDate end);

    /**
     * 批量删除学生
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 新增员工
     * @param emp
     */
    void add(Emp emp);

    /**
     * 根据id获取员工信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 修改员工信息
     * @param emp
     */
    void update(Emp emp);


    /**
     * 登录
     * @param emp
     * @return
     */
    Emp login(Emp emp);

}
