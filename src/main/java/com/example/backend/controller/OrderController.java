package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Order;
import com.example.backend.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
