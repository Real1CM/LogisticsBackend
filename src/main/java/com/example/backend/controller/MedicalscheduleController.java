package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Medicalschedule;
import com.example.backend.entity.User;
import com.example.backend.service.IMedicalscheduleService;
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
@RequestMapping("/medicalschedule")
public class MedicalscheduleController {

    @Autowired
    private IMedicalscheduleService iMedicalscheduleService;

    @ApiOperation("添加医疗服务时间信息")
    @PostMapping("/saveMedicalSchedule")
    public Boolean save(@RequestBody Medicalschedule medicalschedule) {
        return iMedicalscheduleService.save(medicalschedule);
    }

    @ApiOperation("删除医疗服务时间信息")
    @DeleteMapping("/removeMedicalSchedule")
    public Boolean remove(@RequestBody Medicalschedule medicalschedule) {
        return iMedicalscheduleService.removeById(medicalschedule);
    }

    @ApiOperation("添加或修改医疗服务时间信息")
    @PostMapping("/updateOrSaveMedicalSchedule")
    public Boolean updateOrSave(@RequestBody Medicalschedule medicalschedule) {
        return iMedicalscheduleService.saveOrUpdate(medicalschedule);
    }

    @ApiOperation("修改医疗服务时间信息")
    @PostMapping("/updateMedicalSchedule")
    public Boolean update(@RequestBody Medicalschedule medicalschedule) {
        return iMedicalscheduleService.updateById(medicalschedule);
    }

    @ApiOperation("查询医疗服务时间信息")
    @GetMapping("/selectMedicalSchedule")
    public List<Medicalschedule> select(@RequestBody Medicalschedule medicalschedule) {
        LambdaQueryWrapper<Medicalschedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Medicalschedule::getMedicalScheduleId, medicalschedule.getMedicalScheduleId());
        return iMedicalscheduleService.list(lambdaQueryWrapper);
    }

    @ApiOperation("医疗服务时间表的分页")
    @PostMapping("/listPageMedicalSchedule")
    private List<Medicalschedule> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Medicalschedule> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("medicalScheduleId");
        String b = (String) map.get("medicalName");
        String c = (String) map.get("time");

        LambdaQueryWrapper<Medicalschedule> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Medicalschedule::getMedicalScheduleId,a)
                .like(Medicalschedule::getMedicalName,b)
                .like(Medicalschedule::getTime,c);

        IPage res = iMedicalscheduleService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public List<Medicalschedule> page() {
        Page<Medicalschedule> page = new Page<>();
        IPage<Medicalschedule> result = iMedicalscheduleService.page(page);
        return result.getRecords();
    }
}
