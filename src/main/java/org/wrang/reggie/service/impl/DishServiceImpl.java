package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wrang.reggie.dto.DishDto;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.entity.DishFlavor;
import org.wrang.reggie.mapper.DishMapper;
import org.wrang.reggie.service.DishFlavorService;
import org.wrang.reggie.service.DishService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
    @Resource
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional
    public void addDish(DishDto dishDto){
        // 对菜品进行添加,由于继承了ServiceImpl，所以可以直接用this.去使用
        this.save(dishDto);
        Long dishId = dishDto.getId();

        // 在菜风味表里面关联菜品
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach(item -> {
            item.setDishId(dishId);
        });
        // 将flavors每项添加一个dishId还可以这么写，流最后的.collect(Collectors.toList())是收集成List，必须写
        flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
