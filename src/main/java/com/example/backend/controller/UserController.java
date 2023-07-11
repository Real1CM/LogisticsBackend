package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.common.MD5utils;
import com.example.backend.entity.User;
import com.example.backend.service.IUserService;
import io.swagger.annotations.Api;
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
@Api("用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    //增,注册,MD5
    @ApiOperation("添加用户信息")
    @PostMapping("/saveUser")
    public boolean save(@RequestBody User user) {
        user.setPswd(MD5utils.code(user.getPswd())); //加密
        return iUserService.save(user);
    }

    //删
    @ApiOperation("删除用户信息")
    @DeleteMapping("/removeUser")
    public boolean remove(Integer userId) {
        return iUserService.removeById(userId);
    }

    //改或增
    @ApiOperation("添加或修改用户信息")
    @PostMapping("/saveOrUpdateUser")
    public boolean saveOrUpdate(@RequestBody User user) {
        return iUserService.saveOrUpdate(user);
    }

    //改
    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public boolean update(@RequestBody User user) {
        return iUserService.updateById(user);
    }

    //模糊查询
    @ApiOperation("模糊查询用户信息")
    @GetMapping("/selectUser")
    public List<User> select(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getUserName, user.getUserName());
        return iUserService.list(lambdaQueryWrapper);
    }
}
