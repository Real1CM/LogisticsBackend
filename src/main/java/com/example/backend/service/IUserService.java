package com.example.backend.service;

import com.example.backend.VO.ResultVO;
import com.example.backend.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.form.RuleForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
public interface IUserService extends IService<User> {
    public ResultVO login(RuleForm ruleForm);
}
