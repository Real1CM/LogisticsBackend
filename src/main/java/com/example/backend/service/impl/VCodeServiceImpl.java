package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.VCode;
import com.example.backend.mapper.VCodeMapper;
import com.example.backend.service.IVCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-18
 */
@Service
public class VCodeServiceImpl extends ServiceImpl<VCodeMapper, VCode> implements IVCodeService {
    @Override
    public Boolean vertify(VCode vCode) {
        QueryWrapper<VCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", vCode.getAccount());
        VCode the = baseMapper.selectOne(queryWrapper);
        if(vCode.getVCode() == the.getVCode())
            return true;
        return null;
    }
}
