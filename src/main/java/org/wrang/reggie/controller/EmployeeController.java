package org.wrang.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.wrang.reggie.common.R;
import org.wrang.reggie.entity.Employee;
import org.wrang.reggie.service.EmployeeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @PostMapping("login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){
        // 将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(lambdaQueryWrapper);
        // 判断存不存在
        if (emp == null){
            return R.error("用户不存在");
        }
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if (emp.getStatus() == 0){
            return R.error("您已被禁用");
        }
        // 返回token,暂时token用登录的Id代替
        return R.success(emp);
    }

    @PostMapping("loginout")
    public R logout(@RequestBody Employee employee,HttpServletRequest request){
        // 其实这个接口没什么必要
        return R.success("退出成功");
    }
    @GetMapping("page")
    public R<Page> getEmployeeList(int page , int pageSize,String name,HttpServletRequest request){
        // 创建分页构造器
        Page pageInfo = new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        //添加排序条件 （这里根据更新时间排序）
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        employeeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @PostMapping
    private R addEmployee(@RequestBody Employee employee,HttpServletRequest request){
        // 初始密码123456，并加密
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        // 获取缓存的登录的人的id
        // Long loginId = (Long) request.getSession().getAttribute("employee");
        // 处理需要额外处理的参数
        employee.setPassword(password);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(Long.valueOf(1));
        employee.setUpdateUser(Long.valueOf(1));
        // mybatisPlus 默认是雪花算法生成id，可通过yml中id-type改变id生成策略
        // 但同时注意前端只能接受16位~17位的数值，所以直接用雪花算法传给前端数值类型是不行的，要转成字符串
        employeeService.save(employee);
        return R.success("添加成功");
    }

    @PutMapping
    private R updateEmployee(@RequestBody Employee employee,HttpServletRequest request){
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(Long.valueOf(1));
        employeeService.updateById(employee);
        return R.success("更新成功");
    }

}
