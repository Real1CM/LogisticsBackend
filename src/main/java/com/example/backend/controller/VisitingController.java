package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Visiting;
import com.example.backend.service.IVisitingService;
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
@RequestMapping("/visiting")
public class VisitingController {

    @Autowired
    private IVisitingService iVisitingService;

    @ApiOperation("添加绿色基地信息")
    @PostMapping("/saveVisiting")
    public Boolean save(@RequestBody Visiting visiting) {
        return iVisitingService.save(visiting);
    }

    @ApiOperation("添加或修改绿色基地信息")
    @PostMapping("/updateOrSaveVisiting")
    public Boolean updateOrSave(@RequestBody Visiting visiting) {
        return iVisitingService.saveOrUpdate(visiting);
    }

    @ApiOperation("修改绿色基地信息")
    @PostMapping("/updateVisiting")
    public Boolean update(@RequestBody Visiting visiting) {
        return iVisitingService.updateById(visiting);
    }

    @ApiOperation("删除绿色基地信息")
    @DeleteMapping("/removeVisiting")
    public Boolean remove(@RequestBody Visiting visiting) {
        return iVisitingService.removeById(visiting);
    }

    @ApiOperation("查询绿色基地信息")
    @GetMapping("/selectVisiting")
    public List<Visiting> select(@RequestBody Visiting visiting) {
        LambdaQueryWrapper<Visiting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Visiting::getVisitingname, visiting.getVisitingname());
        return iVisitingService.list(lambdaQueryWrapper);
    }
}
