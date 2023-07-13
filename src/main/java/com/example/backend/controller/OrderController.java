package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Order;
import com.example.backend.entity.User;
import com.example.backend.service.IOrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @ApiOperation("添加订单信息")
    @PostMapping("/saveOrder")
    public Boolean save(@RequestBody Order order) {
        return iOrderService.save(order);
    }

    @ApiOperation("添加或修改订单信息")
    @PostMapping("/updateOrSaveOrder")
    public Boolean updateOrSave(@RequestBody Order order) {
        return iOrderService.saveOrUpdate(order);
    }

    @ApiOperation("修改订单信息")
    @PostMapping("/updateOrder")
    public Boolean update(@RequestBody Order order) {
        return iOrderService.updateById(order);
    }

    @ApiOperation("删除订单信息")
    @DeleteMapping("/removeOrder")
    public Boolean remove(@RequestBody Order order) {
        return iOrderService.removeById(order);
    }

    @ApiOperation("查询订单信息")
    @GetMapping("/selectOrder")
    public List<Order> select(@RequestBody Order order) {
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Order::getOrderId, order.getOrderId());
        return iOrderService.list(lambdaQueryWrapper);
    }

    @ApiOperation("订单的分页功能")
    @PostMapping("/listPageOrder")
    private List<Order> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Order> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("orderId");
        String b = (String) map.get("account");
        String c = (String) map.get("dishDetailId");
        String d = (String) map.get("goodsDetailId");
        String e = (String) map.get("money");
        String f = (String) map.get("data");

        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Order::getOrderId, a)
                .like(Order::getAccount, b)
                .like(Order::getDishDetailId, c)
                .like(Order::getGoodsDetailId, d)
                .like(Order::getMoney, e)
                .like(Order::getData, f);

        IPage res = iOrderService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("设置总金额")
    @PostMapping("/setSum")
    public void setSum(String account) {
        DishDetailController dishDetailController = new DishDetailController();
        GoodsDetailController goodsDetailController = new GoodsDetailController();
        float sum = dishDetailController.sumTotalDish(account) + goodsDetailController.sumTotalGoods(account);
        Order order = new Order();
        order.setMoney(sum);

        QueryWrapper<Order> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", account);
        iOrderService.update(order, queryWrapper);
    }
}
