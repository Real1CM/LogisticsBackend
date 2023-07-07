package com.example.backend.service.impl;

import com.example.backend.entity.Dish;
import com.example.backend.mapper.DishMapper;
import com.example.backend.service.IDishService;
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
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

}
