package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Dish;
import com.example.backend.entity.User;
import com.example.backend.service.IDishService;
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

    @PostMapping("/save")
    public boolean save(@RequestBody Dish dish) {
        return iDishService.save(dish);
    }

    @PostMapping("/updateOrSave")
    public boolean updateOrSave(@RequestBody Dish dish) {
        return iDishService.saveOrUpdate(dish);
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Dish dish) {
        return iDishService.updateById(dish);
    }

    @GetMapping("/remove")
    public boolean remove(@RequestBody Dish dish) {
        return iDishService.removeById(dish);
    }

    @PostMapping("/select")
    public List<Dish> select(@RequestBody Dish dish) {
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Dish::getDishName, dish.getDishName());
        return iDishService.list(lambdaQueryWrapper);
    }


}
