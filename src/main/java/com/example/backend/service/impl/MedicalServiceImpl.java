package com.example.backend.service.impl;

import com.example.backend.entity.Medical;
import com.example.backend.mapper.MedicalMapper;
import com.example.backend.service.IMedicalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class MedicalServiceImpl extends ServiceImpl<MedicalMapper, Medical> implements IMedicalService {

}
