package com.example.service.impl;

import com.example.mapper.DeptMapper;
import com.example.mapper.EmpMapper;
import com.example.pojo.Dept;
import com.example.pojo.DeptLog;
import com.example.service.DeptLogService;
import com.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门管理
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional//spring事物管理
    //@Transactional(rollbackFor = Exception.class)//指定所有异常都回滚
    @Override
    public void delte(Integer id) {
        try {
            deptMapper.deltetById(id);//根据id删除部门

            empMapper.delteteByDeptId(id);//将该部门的员工也删除


        } finally {//无论try中程序运行是否成功都能记录日志
            //记录日志
            DeptLog deptLog=new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行解散部门操作，解散的部门："+id+"号部门");
            deptLogService.insert(deptLog);
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept query(Integer id) {
        return deptMapper.queryById(id);
    }

    @Override
    public void update(Dept dept) {
        Dept deptTarget = deptMapper.queryById(dept.getId());
        deptTarget.setUpdateTime(LocalDateTime.now());
        deptTarget.setName(dept.getName());
        deptMapper.updateById(deptTarget);

    }
}
