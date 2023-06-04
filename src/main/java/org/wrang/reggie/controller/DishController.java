package org.wrang.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.wrang.reggie.common.R;
import org.wrang.reggie.dto.DishDto;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.service.DishService;

import javax.annotation.Resource;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/dish")
public class DishController {
    @Resource
    private DishService dishService;
    @PostMapping
    public R<String> addDish(@RequestBody DishDto dishdto){
        dishService.addDish(dishdto);
        return R.success("创建成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        Page<Dish> pager = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        // 第一个参数如果是false，那么这like语句相当于没写
        wrapper.like(name != null,Dish::getName,name);
        // 根据创建时间倒序
        wrapper.orderByDesc(Dish::getCreateTime);
        dishService.page(pager,wrapper);
        return R.success(pager);

    }


}
