package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.backend.VO.PageVO;
import com.example.backend.VO.QuaryPageVO;
import com.example.backend.entity.GoodsDetail;
import com.example.backend.entity.Medical;
import com.example.backend.entity.User;
import com.example.backend.service.IMedicalService;
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
@RequestMapping("/medical")
public class MedicalController {

    @Autowired
    private IMedicalService iMedicalService;

    @ApiOperation("添加医疗服务信息")
    @PostMapping("/saveMedical")
    public Boolean save(@RequestBody Medical medical) {
        return iMedicalService.save(medical);
    }

    @ApiOperation("添加或修改医疗服务信息")
    @PostMapping("/updateOrSaveMedical")
    public Boolean updateOrSave(@RequestBody Medical medical) {
        return iMedicalService.saveOrUpdate(medical);
    }

    @ApiOperation("修改医疗服务信息")
    @PostMapping("/updateMedical")
    public Boolean update(@RequestBody Medical medical) {
        return iMedicalService.updateById(medical);
    }

    @ApiOperation("删除医疗服务信息")
    @DeleteMapping("/removeMedical")
    public Boolean remove(@RequestBody Medical medical) {
        return iMedicalService.removeById(medical);
    }

    @ApiOperation("查询医疗服务信息")
    @GetMapping("/selectMedical")
    public List<Medical> select(@RequestBody Medical medical) {
        LambdaQueryWrapper<Medical> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Medical::getMedicalName, medical.getMedicalName());
        return iMedicalService.list(lambdaQueryWrapper);
    }

    @ApiOperation("医疗服务的分页")
    @PostMapping("/listPageMedical")
    private List<Medical> listPage(@RequestBody QuaryPageVO quaryPageVO) {
        Page<Medical> page = new Page<>();
        page.setCurrent(quaryPageVO.getPageNum());
        page.setSize(quaryPageVO.getPageSize());

        HashMap map = quaryPageVO.getMap();
        String a = (String) map.get("medicalId");
        String b = (String) map.get("medicalName");
        String c = (String) map.get("medicalTimeId");

        LambdaQueryWrapper<Medical> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Medical::getMedicalId,a)
                .like(Medical::getMedicalName,b)
                .like(Medical::getMedicalTimeId,c);

        IPage res = iMedicalService.page(page,lambdaQueryWrapper);
        System.out.println(res.getTotal());

        return res.getRecords();
    }

    @ApiOperation("分页2")
    @PostMapping("/page")
    public PageVO page(@RequestBody PageVO pageVO) {
        pageVO.setDataSum(iMedicalService.count());
        Page<Medical> page = new Page<>();
        page.setCurrent(pageVO.getPageNum());
        page.setSize(pageVO.getPageSize());
        IPage<Medical> result = iMedicalService.page(page);
        pageVO.setData(result.getRecords());
        return pageVO;
    }
}
