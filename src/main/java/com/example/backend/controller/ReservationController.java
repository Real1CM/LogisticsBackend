package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Reservation;
import com.example.backend.service.IReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private IReservationService iReservationService;

    @ApiOperation("添加预约信息")
    @PostMapping("/saveReservation")
    public Boolean save(@RequestBody Reservation reservation) {
        return iReservationService.save(reservation);
    }

    @ApiOperation("添加或修改预约信息")
    @PostMapping("/updateOrSaveReservation")
    public Boolean updateOrSave(@RequestBody Reservation reservation) {
        return iReservationService.saveOrUpdate(reservation);
    }

    @ApiOperation("修改预约信息")
    @PostMapping("/updateReservation")
    public Boolean update(@RequestBody Reservation reservation) {
        return iReservationService.updateById(reservation);
    }

    @ApiOperation("删除预约信息")
    @DeleteMapping("/removeReservation")
    public Boolean remove(@RequestBody Reservation reservation) {
        return iReservationService.removeById(reservation);
    }

    @ApiOperation("查询预约信息")
    @GetMapping("/selectReservation")
    public List<Reservation> select(@RequestBody Reservation reservation) {
        LambdaQueryWrapper<Reservation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Reservation::getReservationsId, reservation.getReservationsId());
        return iReservationService.list(lambdaQueryWrapper);
    }
}
