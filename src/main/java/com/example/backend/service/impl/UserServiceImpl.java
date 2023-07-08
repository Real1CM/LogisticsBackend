package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.VO.ResultVO;
import com.example.backend.entity.User;
import com.example.backend.form.RuleForm;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultVO login(RuleForm ruleForm) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", ruleForm.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if(user == null) {
            resultVO.setI(-1);
        } else {
            if (!user.getPswd().equals(ruleForm.getPassword())){
                resultVO.setI(-2);
            }else {
                resultVO.setI(0);
                resultVO.setData(user);
            }
        }
        return resultVO;
    }
}
