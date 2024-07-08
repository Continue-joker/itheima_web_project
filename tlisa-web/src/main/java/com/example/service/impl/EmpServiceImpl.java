package com.example.service.impl;

import com.example.mapper.EmpMapper;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageBean page(Long page, Long pageSize) {
//        Long start = (page - 1) * pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
//        return new PageBean(empMapper.count(), empList);
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,String image,
                         LocalDate begin, LocalDate end) {
//        1.设置分页参数
        PageHelper.startPage(page,pageSize);

//        2.执行查询
        List<Emp> empList=empMapper.list(name, gender,image, begin, end);
        Page<Emp> empPage=(Page<Emp>) empList;

        return new PageBean(empPage.getTotal(), empPage.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {

        return empMapper.getByUsernameAndPassword(emp);
    }
}
