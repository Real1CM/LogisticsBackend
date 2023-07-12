package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.common.MD5utils;
import com.example.backend.entity.Admin;
import com.example.backend.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation("添加管理员信息")
    @PostMapping("/saveAdmin")
    public boolean save(@RequestBody Admin admin) {
        admin.setPassword(MD5utils.code(admin.getPassword()));
        return adminService.save(admin);
    }

    @ApiOperation("删除管理员信息")
    @DeleteMapping("/removeAdmin")
    public boolean remove(Integer adminId) {
        return adminService.removeById(adminId);
    }

    @ApiOperation("添加或修改管理员信息")
    @PostMapping("/saveOrUpdateAdmin")
    public boolean saveOrUpdate(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }

    @ApiOperation("修改管理员信息")
    @PostMapping("/updateAdmin")
    public boolean update(@RequestBody Admin admin) {
        return adminService.updateById(admin);
    }

    @ApiOperation("模糊查询管理员信息")
    @GetMapping("/selectAdmin")
    public List<Admin> select(@RequestBody Admin admin) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Admin::getName,admin.getName());
        return adminService.list(lambdaQueryWrapper);
    }

    @ApiOperation("管理员的分页")
    @PostMapping("/listPageAdmin")
    private List<Admin> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Admin> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("id");
        String b = (String) map.get("name");
        String c = (String) map.get("gender");
        String d = (String) map.get("password");
        String e = (String) map.get("email");
        String f = (String) map.get("telephone");
        String g = (String) map.get("address");

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Admin::getId,a)
                .like(Admin::getName,b)
                .like(Admin::getGender,c)
                .like(Admin::getPassword,d)
                .like(Admin::getEmail,e)
                .like(Admin::getTelephone,f)
                .like(Admin::getAddress,g);

        IPage res = adminService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }
}
