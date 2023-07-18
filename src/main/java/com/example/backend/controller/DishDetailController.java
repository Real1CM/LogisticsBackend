package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.DishDetail;
import com.example.backend.entity.GoodsDetail;
import com.example.backend.entity.Order;
import com.example.backend.entity.User;
import com.example.backend.mapper.DishDetailMapper;
import com.example.backend.service.IDishDetailService;
import io.swagger.annotations.Api;
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
@Api
@RestController
@RequestMapping("/dish-detail")
public class DishDetailController {

    @Autowired
    private IDishDetailService iDishDetailService;

    //增
    @ApiOperation("添加菜品明细")
    @PostMapping("/saveDishDetail")
    public Boolean save(@RequestBody DishDetail dishDetail) {
        return iDishDetailService.save(dishDetail);
    }

    //删
    @ApiOperation("删除菜品明细")
    @DeleteMapping("/removeDishDetail")
    public Boolean remove(Integer integer) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_ID", integer);
        return iDishDetailService.removeByMap(map);
    }

    //改
    @ApiOperation("修改菜品明细")
    @PostMapping("/updateDishDetail")
    public boolean update(@RequestBody DishDetail dishDetail) {
        return iDishDetailService.updateById(dishDetail);
    }

    //模糊查询
    @ApiOperation("查询菜品明细")
    @GetMapping("/selectDishDetail")
    public List<DishDetail> select(@RequestBody DishDetail dishDetail) {
        LambdaQueryWrapper<DishDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(DishDetail::getDishDetailId, dishDetail.getDishDetailId());
        return iDishDetailService.list(lambdaQueryWrapper);
    }

    //分页
    @ApiOperation("菜品明细的分页")
    @PostMapping("/listPageDishDetail")
    private List<DishDetail> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<DishDetail> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("dishDetailId");
        String b = (String) map.get("account");
        String c = (String) map.get("dishId");
        String d = (String) map.get("number");
        String e = (String) map.get("status");
        String f = (String) map.get("money");

        LambdaQueryWrapper<DishDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(DishDetail::getDishDetailId, a)
                .like(DishDetail::getAccount, b)
                .like(DishDetail::getDishId, c)
                .like(DishDetail::getNumber, d)
                .like(DishDetail::getStatus, e)
                .like(DishDetail::getMoney, f);

        IPage res = iDishDetailService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<DishDetail> page(int size, int num) {
        Page<DishDetail> page = new Page<>();
        page.setCurrent(num);
        page.setSize(size);
        IPage<DishDetail> result = iDishDetailService.page(page);
        return result.getRecords();
    }

    @ApiOperation("根据Order表里的账户找到全部dish_detail")
    @PostMapping("/getAllDetail")
    public List<DishDetail> getAllDetail(@RequestBody Order order) {
        LambdaQueryWrapper<DishDetail> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(DishDetail::getAccount, order.getAccount());
        return iDishDetailService.list(lambdaQueryWrapper);
    }

    @ApiOperation("查某一用户的菜品总金额")
    @PostMapping("/sumDishes")  //没写完
    public float sumDishes(@RequestBody Order order) {
        QueryWrapper<DishDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("IFNULL(sum(money*number),0) as money")
                .eq("account", order.getAccount());
        DishDetail detail = iDishDetailService.getOne(queryWrapper);
        return detail.getMoney();
    }
}
