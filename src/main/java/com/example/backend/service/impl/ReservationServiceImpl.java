package com.example.backend.service.impl;

import com.example.backend.entity.Reservation;
import com.example.backend.mapper.ReservationMapper;
import com.example.backend.service.IReservationService;
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
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements IReservationService {

}
