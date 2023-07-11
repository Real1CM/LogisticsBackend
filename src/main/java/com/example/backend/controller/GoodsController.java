package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Goods;
import com.example.backend.service.IGoodsService;
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
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IGoodsService iGoodsService;

    @ApiOperation("添加商品信息")
    @PostMapping("/saveGoods")
    public Boolean save(@RequestBody Goods goods) {
        return iGoodsService.save(goods);
    }

    @ApiOperation("添加或修改商品信息")
    @PostMapping("/updateOrSaveGoods")
    public Boolean updateOrSave(@RequestBody Goods goods) {
        return iGoodsService.saveOrUpdate(goods);
    }

    @ApiOperation("修改商品信息")
    @PostMapping("/updateGoods")
    public Boolean update(@RequestBody Goods goods) {
        return iGoodsService.updateById(goods);
    }

    @ApiOperation("删除商品信息")
    @DeleteMapping("/removeGoods")
    public Boolean remove(@RequestBody Goods goods) {
        return iGoodsService.removeById(goods);
    }

    @ApiOperation("查询商品信息")
    @GetMapping("/selectGoods")
    public List<Goods> select(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getGoodsName, goods.getGoodsName());
        return iGoodsService.list(lambdaQueryWrapper);
    }
}
