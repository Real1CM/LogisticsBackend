package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.User;
import com.example.backend.service.IUserService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    //增
    @PostMapping("/save")
    public boolean save(@RequestBody User user) {
        return iUserService.save(user);
    }

    //删
    @GetMapping("/remove")
    public boolean remove(Integer i) {
        return iUserService.removeById(i);
    }

    //改或增
    @PostMapping("/saveOrUpdate")
    public boolean saveOrUpdate(@RequestBody User user) {
        return iUserService.saveOrUpdate(user);
    }

    //增
    @PostMapping("/update")
    public boolean update(@RequestBody User user) {
        return iUserService.updateById(user);
    }

    //模糊查询
    @PostMapping("/select")
    public List<User> select(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getUserName,user.getUserName());
        return iUserService.list(lambdaQueryWrapper);
    }
}
