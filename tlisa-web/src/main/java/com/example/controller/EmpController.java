package com.example.controller;

import com.example.anno.Log;
import com.example.pojo.Emp;
import com.example.pojo.PageBean;
import com.example.pojo.Result;
import com.example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询学生列表
     * @param page  分页查询的页码，设置默认值为1
     * @param pageSize 页面大小，设置默认值为10
     * @return
     */
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,String image,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询：{}，{},{},{},{},{},{}",page,pageSize,name,gender,image,begin,end);
        PageBean pageBean= empService.page(page,pageSize,name,gender,image,begin,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delte(@PathVariable List<Integer> ids){
        log.info("批量删除，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Emp emp, HttpSession session){
        emp.setImage(session.getAttribute("image").toString());
        log.info("添加员工：emp-{}",emp);
        empService.add(emp);
        return Result.success(emp);
    }

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询员工：id-{}",id);
        Emp emp= empService.getById(id);
        return Result.success(emp);
    }


    /**
     * 修改员工信息
     * @param emp
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("待修改的员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }

}
