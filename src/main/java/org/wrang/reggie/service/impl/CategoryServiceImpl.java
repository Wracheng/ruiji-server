package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wrang.reggie.entity.Category;
import org.wrang.reggie.mapper.CategoryMapper;
import org.wrang.reggie.service.CategoryService;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Category> wapper = new LambdaQueryWrapper<>();
        // 找到有没有菜绑定了菜类，有不能删报异常

        // 找到有没有套餐绑定了菜类，有不能删报异常
    }
}
