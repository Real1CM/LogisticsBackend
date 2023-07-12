package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Visitingschedule;
import com.example.backend.service.IVisitingscheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
@RequestMapping("/visitingschedule")
public class VisitingscheduleController {

    @Autowired
    private IVisitingscheduleService iVisitingscheduleService;

    @ApiOperation("添加绿化基地时间信息")
    @PostMapping("/saveVisitingSchedule")
    public Boolean save(@RequestBody Visitingschedule visitingschedule) {
        return iVisitingscheduleService.save(visitingschedule);
    }

    @ApiOperation("删除绿化基地时间信息")
    @DeleteMapping("/removeVisitingSchedule")
    public Boolean remove(@RequestBody Visitingschedule visitingschedule) {
        return iVisitingscheduleService.removeById(visitingschedule);
    }

    @ApiOperation("添加或修改绿化基地时间信息")
    @PostMapping("/updateOrSaveVisitingSchedule")
    public Boolean updateOrSave(@RequestBody Visitingschedule visitingschedule) {
        return iVisitingscheduleService.saveOrUpdate(visitingschedule);
    }

    @ApiOperation("修改医疗服务时间信息")
    @PostMapping("/updateVisitingSchedule")
    public Boolean update(@RequestBody Visitingschedule visitingschedule) {
        return iVisitingscheduleService.updateById(visitingschedule);
    }

    @ApiOperation("查询绿化基地时间信息")
    @GetMapping("/selectVisitingSchedule")
    public List<Visitingschedule> select(@RequestBody Visitingschedule visitingschedule) {
        LambdaQueryWrapper<Visitingschedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Visitingschedule::getVisitingScheduleId, visitingschedule.getVisitingScheduleId());
        return iVisitingscheduleService.list(lambdaQueryWrapper);
    }

    @ApiOperation("绿色基地时间表的分页")
    @PostMapping("/listPageVisitingSchedule")
    private List<Visitingschedule> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Visitingschedule> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("visitingScheduleId");
        String b = (String) map.get("visitingId");
        String c = (String) map.get("time");

        LambdaQueryWrapper<Visitingschedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Visitingschedule::getVisitingScheduleId,a)
                .like(Visitingschedule::getVisitingId,b)
                .like(Visitingschedule::getTime,c);

        IPage res = iVisitingscheduleService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }
}
