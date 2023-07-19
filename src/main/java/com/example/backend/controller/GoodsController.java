package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Goods;
import com.example.backend.entity.GoodsDetail;
import com.example.backend.mapper.GoodsMapper;
import com.example.backend.service.IGoodsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @ApiOperation("商品的分页")
    @PostMapping("/listPageGoods")
    private List<Goods> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Goods> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("goodsId");
        String b = (String) map.get("goodsName");
        String c = (String) map.get("money");

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Goods::getGoodsId, a)
                .like(Goods::getGoodsName, b)
                .like(Goods::getMoney, c);

        IPage res = iGoodsService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<Goods> page(@RequestBody PageVO pageVO) {
        Page<Goods> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Goods> result = iGoodsService.page(page);
        return result.getRecords();
    }

    @Autowired
    private GoodsMapper goodsMapper;

    @ApiOperation("返回所有数据!")
    @GetMapping("/getAll")
    public List<Goods> getAll() {
        List<Goods> goods = goodsMapper.selectList(null);
        return goods;
    }

    @ApiOperation("分类查询")
    @PostMapping("/selectByClass")
    public List<Goods> selectByClass(@RequestBody Goods goods) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGoodsClass,goods.getGoodsClass());
        List<Goods> good =goodsMapper.selectList(lambdaQueryWrapper);
        return good;
    }

    @ApiOperation("根据商品明细中的商品id字段查询商品信息")
    @PostMapping("/selectByDetail")
    public Goods selectByDetail(@RequestBody GoodsDetail goodsDetail) {
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Goods::getGoodsId, goodsDetail.getGoodsId());
        return goodsMapper.selectOne(lambdaQueryWrapper);
    }
}
