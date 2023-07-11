package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Dish;
import com.example.backend.service.IDishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private IDishService iDishService;

    @ApiOperation("添加菜品信息")
    @PostMapping("/saveDish")
    public boolean save(@RequestBody Dish dish) {
        return iDishService.save(dish);
    }

    @ApiOperation("添加或修改菜品信息")
    @PostMapping("/updateOrSaveDish")
    public boolean updateOrSave(@RequestBody Dish dish) {
        return iDishService.saveOrUpdate(dish);
    }

    @ApiOperation("修改菜品信息")
    @PostMapping("/updateDish")
    public boolean update(@RequestBody Dish dish) {
        return iDishService.updateById(dish);
    }

    @ApiOperation("删除菜品信息")
    @DeleteMapping("/removeDish")
    public boolean remove(@RequestBody Dish dish) {
        return iDishService.removeById(dish);
    }

    @ApiOperation("查询菜品信息")
    @GetMapping("/selectDish")
    public List<Dish> select(@RequestBody Dish dish) {
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Dish::getDishName, dish.getDishName());
        return iDishService.list(lambdaQueryWrapper);
    }


}
