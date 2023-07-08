package com.example.backend.controller;


import com.example.backend.entity.DishDetail;
import com.example.backend.service.IDishDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/dish-detail")
public class DishDetailController {

    @Autowired
    private IDishDetailService iDishDetailService;

    //增
    @PostMapping("/save")
    public Boolean save(@RequestBody DishDetail dishDetail) {
        return iDishDetailService.save(dishDetail);
    }

    //删
    @GetMapping("/remove")
    public Boolean remove(Integer integer) {
        Map<String,Object> map = new HashMap<>();
        map.put("user_ID",integer);
        return iDishDetailService.removeByMap(map);
    }
    //查
}
