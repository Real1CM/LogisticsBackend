package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.common.MD5utils;
import com.example.backend.entity.Admin;
import com.example.backend.entity.LoginForm;
import com.example.backend.mapper.AdminMapper;
import com.example.backend.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("adminServiceImpl")
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public Admin login(LoginForm loginForm) {
        QueryWrapper<Admin> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("name",loginForm.getUsername());
        queryWrapper.eq("password", MD5utils.code(loginForm.getPassword()));
        //将字符串由明文转成密文再去与密码对比

        Admin admin = baseMapper.selectOne(queryWrapper);
        return admin;
    }

    @Override
    public Admin getAdminById(Long useId) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<Admin>();
        queryWrapper.eq("id",useId);
        return baseMapper.selectOne(queryWrapper);
    }

}

