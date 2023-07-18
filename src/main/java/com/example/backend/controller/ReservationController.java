package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Reservation;
import com.example.backend.service.IReservationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @ApiOperation("预约的分页功能")
    @PostMapping("/listPageReservation")
    private List<Reservation> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Reservation> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("reservationId");
        String b = (String) map.get("medicalId");
        String c = (String) map.get("visitingId");
        String d = (String) map.get("userId");

        LambdaQueryWrapper<Reservation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Reservation::getReservationsId, a)
                .like(Reservation::getMedicalId, b)
                .like(Reservation::getVisitingId, c)
                .like(Reservation::getUserId, d);

        IPage res = iReservationService.page(page, lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<Reservation> page(@RequestBody PageVO pageVO) {
        Page<Reservation> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Reservation> result = iReservationService.page(page);
        return result.getRecords();
    }

    @ApiOperation("分页3")
    @PostMapping("/page3")
    public PageVO page3(@RequestBody PageVO pageVO) {
        Page<Reservation> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Reservation> result = iReservationService.page(page);
        pageVO.setData(result.getRecords());
        return pageVO;
    }
}
