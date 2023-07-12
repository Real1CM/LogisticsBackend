package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.GoodsDetail;
import com.example.backend.service.IGoodsDetailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
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
        lambdaQueryWrapper.like(GoodsDetail::getGoodsDetailId,goodsDetail.getGoodsDetailId());
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
        String b = (String) map.get("userId");
        String c = (String) map.get("goodsId");
        String d = (String) map.get("number");
        String e = (String) map.get("status");
        String f = (String) map.get("money");

        LambdaQueryWrapper<GoodsDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(GoodsDetail::getGoodsDetailId,a)
                .like(GoodsDetail::getUserId,b)
                .like(GoodsDetail::getGoodsId,c)
                .like(GoodsDetail::getNumber,d)
                .like(GoodsDetail::getStatus,e)
                .like(GoodsDetail::getMoney,f);

        IPage res = iGoodsDetailService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }
}
