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
}
