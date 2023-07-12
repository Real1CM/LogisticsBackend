package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.VO.AccountLoginVO;
import com.example.backend.common.MD5utils;
import com.example.backend.entity.LoginForm;
import com.example.backend.entity.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User login(LoginForm loginForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", loginForm.getUsername());
        queryWrapper.eq("pswd", MD5utils.code(loginForm.getPassword()));

        User user = baseMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public User getUserById(Long useId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("user_id", useId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Boolean accountLogin(AccountLoginVO accountLoginVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", accountLoginVO.getAccount())
                .eq("pswd", MD5utils.code(accountLoginVO.getPswd()));
        User user = baseMapper.selectOne(queryWrapper);
        if (user == null) return false;
        if (user.getStatus() == "1")
            accountLoginVO.setStatus(1);
        else
            accountLoginVO.setStatus(0);
        return true;
    }
}
