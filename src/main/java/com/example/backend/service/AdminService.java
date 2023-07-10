package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.entity.Admin;
import com.example.backend.entity.LoginForm;


public interface AdminService extends IService<Admin>{
    Admin login(LoginForm loginForm);

    Admin getAdminById(Long useId);
}
