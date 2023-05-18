package org.wrang.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.wrang.reggie.common.R;
import org.wrang.reggie.entity.Category;
import org.wrang.reggie.service.CategoryService;

import javax.annotation.Resource;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryService categoryService;
    @PostMapping
    public R<String> addCategory(@RequestBody Category category){
        log.info(String.valueOf(category));
        categoryService.save(category);
        return R.success("添加成功");
    }
    @GetMapping("page")
    public R<Page> categoryList(int page , int pageSize){
        // 创建一个分页器
        Page pageInfo = new Page(page, pageSize);
        // 创建一个sql过滤器，注意泛型里面要填实体类，不然会找不到getSort方法
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo,wrapper);
        return R.success(pageInfo);
    }
    @PutMapping
    public R<String> updateCategory(@RequestBody Category category){
        categoryService.updateById(category);
        return R.success("更新成功");
    }
    @
    DeleteMapping
    public R<String> deleteCategory(Long ids){
        categoryService.remove(ids);
        return R.success("删除成功");
    }
}
