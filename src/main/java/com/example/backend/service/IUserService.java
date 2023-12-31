package com.example.backend.service;

import com.example.backend.VO.AccountLoginVO;
import com.example.backend.entity.LoginForm;
import com.example.backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

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

    int accountLogin(AccountLoginVO accountLoginVO);

    User register(User user);
}
