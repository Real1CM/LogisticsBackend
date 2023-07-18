package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.VCode;
import com.example.backend.mapper.VCodeMapper;
import com.example.backend.service.IVCodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-18
 */
@Service
@Transactional
public class VCodeServiceImpl extends ServiceImpl<VCodeMapper, VCode> implements IVCodeService {

}
