package org.wrang.reggie.service.impl;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.wrang.reggie.entity.Setmeal;
import org.wrang.reggie.mapper.SetmealMapper;
import org.wrang.reggie.service.SetmealService;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper,Setmeal> implements SetmealService{

}