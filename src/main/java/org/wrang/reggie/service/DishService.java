package org.wrang.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wrang.reggie.dto.DishDto;
import org.wrang.reggie.entity.Category;
import org.wrang.reggie.entity.Dish;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
public interface DishService extends IService<Dish> {
    void addDish(DishDto dishDto);
    // 根据菜品id查询口味信息
    DishDto getByIdWidthFlavor(Long id);
    void updateDish(DishDto dishDto);
}
