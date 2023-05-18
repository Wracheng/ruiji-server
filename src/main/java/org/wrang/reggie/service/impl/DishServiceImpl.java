package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.entity.Employee;
import org.wrang.reggie.mapper.DishMapper;
import org.wrang.reggie.mapper.EmployeeMapper;
import org.wrang.reggie.service.DishService;
import org.wrang.reggie.service.EmployeeService;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
