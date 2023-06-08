package org.wrang.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.wrang.reggie.common.R;
import org.wrang.reggie.dto.DishDto;
import org.wrang.reggie.entity.Category;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.service.CategoryService;
import org.wrang.reggie.service.DishService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private CategoryService categoryService;
    @PostMapping
    public R<String> addDish(@RequestBody DishDto dishdto){
        dishService.addDish(dishdto);
        return R.success("创建成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        // 这里不直接用DishDto作为类型是因为IService是用Dish的，如果强制改IService的类型，可能会对其他逻辑产生影响
        Page<Dish> pager = new Page<>(page,pageSize);
        Page<DishDto> pagerdto = new Page<>(page,pageSize);
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        // 第一个参数如果是false，那么这like语句相当于没写,等同于if(name != null){wrapper.like(true,Dish::getName,name);}
        wrapper.like(name != null,Dish::getName,name);
        // 根据创建时间倒序
        wrapper.orderByDesc(Dish::getCreateTime);
        dishService.page(pager,wrapper);
        // ------- 通过实体类生成的分页器拼凑出Dto的分页器，忽略records，records是分页器里的一个属性，我们需要自己填充
        BeanUtils.copyProperties(pager, pagerdto, "records");
        // 手动通过Dish生成的分页器中拿取数据重新构造返回值数组中每一项的内容，使每一项和dto对象对应
        List<DishDto> list = pager.getRecords().stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Category categoryInfo = categoryService.getById(item.getCategoryId());
            dishDto.setCategoryName(categoryInfo.getName());
            return dishDto;
        }).collect(Collectors.toList());
        // 将手动改好的list放到dto生成的page分页器中
        pagerdto.setRecords(list);
        return R.success(pagerdto);

    }
    // 查询菜品详情
    @GetMapping("/{id}")
    public R<DishDto> getInfo(@PathVariable Long id){
        DishDto dishDto = dishService.getByIdWidthFlavor(id);
        return R.success(dishDto);
    }

    // 修改菜品信息
    @PutMapping
    public R<String> updateDish(@RequestBody DishDto dishdto){
        dishService.updateDish(dishdto);
        return R.success("更新成功");
    }

}
