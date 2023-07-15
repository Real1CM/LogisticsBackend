package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.GoodsDetail;
import com.example.backend.entity.Order;
import com.example.backend.service.IGoodsDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/goods-detail")
public class GoodsDetailController {

    @Autowired
    private IGoodsDetailService iGoodsDetailService;

    @ApiOperation("添加商品明细信息")
    @PostMapping("/saveGoodsDetail")
    public boolean save(@RequestBody GoodsDetail goodsDetail) {
        return iGoodsDetailService.save(goodsDetail);
    }

    @ApiOperation("删除商品明细信息")
    @DeleteMapping("/removeGoodsDetail")
    public boolean remove(@RequestBody GoodsDetail goodsDetail) {
        return iGoodsDetailService.removeById(goodsDetail);
    }

    @ApiOperation("添加或修改商品明细信息")
    @PostMapping("/saveOrUpdateGoodsDetail")
    public boolean saveOrUpdate(@RequestBody GoodsDetail goodsDetail) {
        return iGoodsDetailService.saveOrUpdate(goodsDetail);
    }

    @ApiOperation("修改商品明细信息")
    @PostMapping("/updateGoodsDetail")
    public boolean update(@RequestBody GoodsDetail goodsDetail) {
        return iGoodsDetailService.updateById(goodsDetail);
    }

    @ApiOperation("模糊查询商品明细信息")
    @GetMapping("/selectGoodsDetail")
    public List<GoodsDetail> select(@RequestBody GoodsDetail goodsDetail) {
        LambdaQueryWrapper<GoodsDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(GoodsDetail::getGoodsDetailId, goodsDetail.getGoodsDetailId());
        return iGoodsDetailService.list(lambdaQueryWrapper);
    }

    @ApiOperation("商品明细的分页功能")
    @PostMapping("/listPageGoodsDetail")
    private List<GoodsDetail> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<GoodsDetail> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("goodsDetailId");
        String b = (String) map.get("account");
        String c = (String) map.get("goodsId");
        String d = (String) map.get("number");
        String e = (String) map.get("status");
        String f = (String) map.get("money");

        LambdaQueryWrapper<GoodsDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(GoodsDetail::getGoodsDetailId, a)
                .like(GoodsDetail::getAccount, b)
                .like(GoodsDetail::getGoodsId, c)
                .like(GoodsDetail::getNumber, d)
                .like(GoodsDetail::getStatus, e)
                .like(GoodsDetail::getMoney, f);

        IPage res = iGoodsDetailService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("根据Order表里的userId找到全部goods_detail")
    @PostMapping("/getAllDetail")
    public List<GoodsDetail> getAllDetail(@RequestBody Order order) {
        LambdaQueryWrapper<GoodsDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(GoodsDetail::getAccount, order.getAccount());
        return iGoodsDetailService.list(lambdaQueryWrapper);
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<GoodsDetail> page(@RequestBody PageVO pageVO) {
        Page<GoodsDetail> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<GoodsDetail> result = iGoodsDetailService.page(page);
        return result.getRecords();
    }

    @ApiOperation("查某一用户的商品总金额")
    @PostMapping("/sumGoods")
    public float sumGoods(@RequestBody GoodsDetail goodsDetail) {
        QueryWrapper<GoodsDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", goodsDetail.getAccount());
        queryWrapper.select("IFNULL(sum(money),0.0) as money");
        GoodsDetail detail = iGoodsDetailService.getOne(queryWrapper);
        return detail.getMoney();
    }
}
