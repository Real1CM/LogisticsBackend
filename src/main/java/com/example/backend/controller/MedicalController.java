package com.example.backend.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.backend.entity.Medical;
import com.example.backend.service.IMedicalService;
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
}
