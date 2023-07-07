package com.example.backend.service.impl;

import com.example.backend.entity.Goods;
import com.example.backend.mapper.GoodsMapper;
import com.example.backend.service.IGoodsService;
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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

}
