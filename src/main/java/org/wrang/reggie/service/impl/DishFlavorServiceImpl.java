package org.wrang.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wrang.reggie.entity.DishFlavor;
import org.wrang.reggie.mapper.DishFlavorMapper;
import org.wrang.reggie.service.DishFlavorService;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
