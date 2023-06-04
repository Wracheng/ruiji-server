package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wrang.reggie.common.Exption.MyCustomException;
import org.wrang.reggie.entity.Category;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.entity.Setmeal;
import org.wrang.reggie.mapper.CategoryMapper;
import org.wrang.reggie.service.CategoryService;
import org.wrang.reggie.service.DishService;
import org.wrang.reggie.service.SetmealService;

import javax.annotation.Resource;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private DishService dishService;
    @Resource
    private SetmealService setmealService;
    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> wapper = new LambdaQueryWrapper<>();
        // 找到有没有菜绑定了菜类，有不能删报异常 from dish where id = id
        wapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(wapper);
        if (count > 0){
           throw new MyCustomException("当前分类已绑定菜品，不能删除");
        }
        // 找到有没有套餐绑定了菜类，有不能删报异常
        LambdaQueryWrapper<Setmeal> wapper2 = new LambdaQueryWrapper<>();
        wapper2.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(wapper2);
        if (count2 > 0){
            throw new MyCustomException("当前分类已绑定套餐，不能删除");
        }
    }
}
