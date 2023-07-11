package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Goods;
import com.example.backend.service.IGoodsService;
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
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService iGoodsService;

    @PostMapping("/save")
    public Boolean save(@RequestBody Goods goods) {
        return iGoodsService.save(goods);
    }

    @PostMapping("/updateOrSave")
    public Boolean updateOrSave(@RequestBody Goods goods) {
        return iGoodsService.saveOrUpdate(goods);
    }

    @PostMapping("/update")
    public Boolean update(@RequestBody Goods goods) {
        return iGoodsService.updateById(goods);
    }

    @GetMapping("/remove")
    public Boolean remove(@RequestBody Goods goods) {
        return iGoodsService.removeById(goods);
    }

    @PostMapping("/select")
    public List<Goods> select(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getGoodsId, goods.getGoodsId());
        return iGoodsService.list(lambdaQueryWrapper);
    }
}
