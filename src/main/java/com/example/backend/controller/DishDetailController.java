package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.DishDetail;
import com.example.backend.service.IDishDetailService;
import io.swagger.annotations.Api;
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
@Api
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

    //模糊查询
    @PostMapping("/select")
    public List<DishDetail> select(@RequestBody DishDetail dishDetail) {
        LambdaQueryWrapper<DishDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(DishDetail::getDishDetailId, dishDetail.getDishDetailId());
        return iDishDetailService.list(lambdaQueryWrapper);
    }

    //分页
    @PostMapping("/listPage")
    private List<DishDetail> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<DishDetail> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("dishDetailId");
        String b = (String) map.get("userId");
        String c = (String) map.get("dishId");
        String d = (String) map.get("number");
        String e = (String) map.get("status");
        String f = (String) map.get("money");

        LambdaQueryWrapper<DishDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(DishDetail::getDishDetailId,a)
                .like(DishDetail::getUserId , b)
                .like(DishDetail::getDishId,c)
                .like(DishDetail::getNumber,d)
                .like(DishDetail::getStatus,e)
                .like(DishDetail::getMoney,f);

        IPage res = iDishDetailService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }
}
