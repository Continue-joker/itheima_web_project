package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Dept;
import com.example.pojo.Result;
import com.example.service.DeptService;
import com.example.service.EmpService;
import com.zaxxer.hikari.util.ClockSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    //    private static Logger log=LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;


//    @RequestMapping(value = "/depts" ,method = RequestMethod.GET)

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping
    public Result list() {
        List< Dept>deptList=deptService.list();
        log.info("查询所有部门");
        return Result.success(deptList);
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门：{}",id);//{}是id的占位符
        deptService.delte(id);//根据id删除部门
        return Result.success();
    }

    /**
     * 添加部门
     * @param dept
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门：{}",dept.getName());
        deptService.add(dept);
        return Result.success(dept);
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result query(@PathVariable Integer id){
        Dept dept= deptService.query(id);
        log.info("根据id查询部门：{}",dept.getName());

        return Result.success(dept);
    }

    /**
     * 更新部门信息
     * @param dept
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        deptService.update(dept);
        return Result.success(dept);
    }
}
