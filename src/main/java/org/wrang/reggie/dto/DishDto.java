package org.wrang.reggie.dto;

import lombok.Data;
import org.wrang.reggie.entity.Dish;
import org.wrang.reggie.entity.DishFlavor;

import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    //菜品对应的口味数据
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
