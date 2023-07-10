package com.example.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.entity.LoginForm;
import com.example.backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
public interface IUserService extends IService<User> {
    User login(LoginForm loginForm);

    User getUserById(Long useId);

}
