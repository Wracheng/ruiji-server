package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wrang.reggie.entity.Employee;
import org.wrang.reggie.mapper.EmployeeMapper;
import org.wrang.reggie.service.EmployeeService;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
