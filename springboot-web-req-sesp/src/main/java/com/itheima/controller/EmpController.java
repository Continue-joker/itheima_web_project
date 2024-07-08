package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.service.impl.EmpServiceA;
import com.itheima.utiles.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService; //=new EmpServiceA();

    @RequestMapping("listEmp")
    public Result list(){
        //1.调用service，获取数据
        List<Emp> empList = empService.listEmp();

        //2.响应数据
        return Result.success(empList);

    }

//    @RequestMapping("/listEmp")
//    public Result list() {
//        //1.加载并解析emp.xml
//        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
//        System.out.println(file);
//        List<Emp> empList=XmlParserUtils.parse(file, Emp.class);
//
//        //2.数据处理
//        empList.stream().forEach(emp -> {
//            //处理性别 1男 2女
//            String gender= emp.getGender();
//            if("1".equals(gender)){
//                emp.setGender("男");
//            } else if ("2".equals(gender)) {
//                emp.setGender("女");
//            }
//
//            //处理工作：1讲师 2班主任 3就业指导
//            String job = emp.getJob();
//            if ("1".equals(job)) {
//                emp.setJob("讲师");
//            } else if ("2".equals(job)) {
//                emp.setJob("班主任");
//            } else if ("3".equals(job)) {
//                emp.setJob("就业指导");
//            }
//
//        });
//
//        //3.响应数据
//        return Result.success(empList);
//    }
//


}




