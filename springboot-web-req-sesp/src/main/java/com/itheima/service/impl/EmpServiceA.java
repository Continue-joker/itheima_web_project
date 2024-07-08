package com.itheima.service.impl;

import com.itheima.dao.EmpDao;
import com.itheima.dao.impl.EmpDaoA;
import com.itheima.pojo.Emp;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component  //将当前类交给IOC容器管理，成为IOC容器中的bean  -->控制反转
public class EmpServiceA implements EmpService {

    @Autowired  //程序运行时，IOC容器会提供该类型的bean对象，并赋值给该变量  -->依赖注入
    private EmpDao empDao;//=new EmpDaoA();
    @Override
    public List<Emp> listEmp() {
        //1.调用dao,获取数据
        List<Emp> empList = empDao.listEmp();

        //2.数据处理
        empList.stream().forEach(emp -> {
            //处理性别 1男 2女
            String gender= emp.getGender();
            if("1".equals(gender)){
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }

            //处理工作：1讲师 2班主任 3就业指导
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }

        });
        return empList;
    }
}
