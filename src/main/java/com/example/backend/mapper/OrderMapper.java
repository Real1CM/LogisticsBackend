package com.example.backend.mapper;

import com.example.backend.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    Boolean updateByAccount(@Param("u") Order order);
}
