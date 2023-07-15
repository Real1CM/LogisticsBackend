package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.AccountLoginVO;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.common.MD5utils;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.IUserService;
import io.swagger.annotations.Api;
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
@Api("用户API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private UserMapper userMapper;

    //增,注册,MD5
    @ApiOperation("添加用户信息")
    @PostMapping("/saveUser")
    public boolean save(@RequestBody User user) {

        User selected = iUserService.register(user);
        if (selected != null) {
            System.out.println("用户已存在!");
            return false;
        }
        user.setPswd(MD5utils.code(user.getPswd())); //加密
        return iUserService.save(user);
    }

    //删
    @ApiOperation("删除用户信息")
    @DeleteMapping("/removeUser")
    public boolean remove(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", user.getAccount());
        return iUserService.remove(queryWrapper);
    }

    //改或增
    @ApiOperation("添加或修改用户信息")
    @PostMapping("/saveOrUpdateUser")
    public boolean saveOrUpdate(@RequestBody User user) {
        user.setPswd(MD5utils.code(user.getPswd()));
        return iUserService.saveOrUpdate(user);
    }

    //改
    @ApiOperation("修改用户信息")
    @PostMapping("/updateUser")
    public boolean update(@RequestBody User user) {
        user.setPswd(MD5utils.code(user.getPswd()));
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("account", user.getAccount());
        return iUserService.update(user, updateWrapper);
    }

    //模糊查询
    @ApiOperation("模糊查询用户信息")
    @GetMapping("/selectUser")
    public List<User> select(@RequestBody User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getAccount, user.getAccount());
        return iUserService.list(lambdaQueryWrapper);
    }

    @ApiOperation("用户的分页")
    @PostMapping("/listPage")
    private List<User> listPage(@RequestBody QuaryPageVO quaryPageVO) {

        Page<User> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("userId");
        String b = (String) map.get("userName");
        //String c = (String) map.get("pswd");
        String d = (String) map.get("account");
        String e = (String) map.get("gender");
        String f = (String) map.get("phone");
        String g =(String) map.get("picture");
        String h = (String) map.get("status");

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getUserId,a)
                .like(User::getUserName,b)
                //.like(User::getPswd, new MD5utils().code(c))
                .like(User::getAccount, d)
                .like(User::getGender, e)
                .like(User::getPhone,f)
                .like(User::getPicture,g)
                .like(User::getStatus, h);

        IPage res = iUserService.page(page, lambdaQueryWrapper);

        return res.getRecords();
    }

    @ApiOperation("返回全部数据")
    @PostMapping("/selectAll")
    public List<User> selectAll() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @ApiOperation("前端请求数据")
    @GetMapping("/getUserMsg")
    public List<User> getUserMsg(@RequestBody String account) {  //入参是一串账号号,比如12375634967
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getAccount, account);
        return iUserService.list(lambdaQueryWrapper);
    }

    @ApiOperation("这个lcm版的登录")
    @PostMapping("/lcmLogin")  //用第五个数据测试,第五个数据的密码是pass,也可以自己添加数据测试,你得记住密码加密后数据库里你是看不到密码的
    public int lcmLogin(@RequestBody AccountLoginVO accountLoginVO) {
        return iUserService.accountLogin(accountLoginVO);
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<User> page(@RequestBody PageVO pageVO) {
        Page<User> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<User> result = iUserService.page(page);
        return result.getRecords();
    }
}
