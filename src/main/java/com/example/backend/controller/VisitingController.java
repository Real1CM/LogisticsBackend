package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.Dish;
import com.example.backend.entity.User;
import com.example.backend.entity.Visiting;
import com.example.backend.service.IVisitingService;
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

    @ApiOperation("绿色基地的分页")
    @PostMapping("/listPageVisiting")
    private List<Visiting> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Visiting> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("visitingId");
        String b = (String) map.get("visitingName");
        String c = (String) map.get("visitingTimeId");

        LambdaQueryWrapper<Visiting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Visiting::getVisitingId,a)
                .like(Visiting::getVisitingname,b)
                .like(Visiting::getVisitingTimeId,c);

        IPage res = iVisitingService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageVO pageVO) {
        pageVO.setDataSum(iVisitingService.count());
        Page<Visiting> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Visiting> result = iVisitingService.page(page);
        pageVO.setData(result.getRecords());
        return pageVO;
    }
}
