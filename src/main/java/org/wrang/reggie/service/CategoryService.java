package org.wrang.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.wrang.reggie.entity.Category;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
public interface CategoryService extends IService<Category> {
    void remove(Long id);
}
