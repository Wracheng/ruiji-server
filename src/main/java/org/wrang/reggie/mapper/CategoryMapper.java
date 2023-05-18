package org.wrang.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.wrang.reggie.entity.Category;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
