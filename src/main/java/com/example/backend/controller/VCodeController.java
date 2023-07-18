package com.example.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.entity.User;
import com.example.backend.entity.VCode;
import com.example.backend.service.IVCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-18
 */
@Api("验证码系统")
@RestController
@RequestMapping("/v-code")
public class VCodeController {
    @Autowired
    private IVCodeService ivCodeService;

    @Autowired
    private SystemController systemController;

    @ApiOperation("发送验证码,存验证码,我这里account指的是电话号码对应表中phone字段")
    @GetMapping("/send")
    public Boolean send(@RequestBody VCode vCode) throws Exception {
        if (vCode.getAccount() == null) return false;
        String code = systemController.sendVerificationCode(vCode.getAccount());
        vCode.setVCode(code);
        return ivCodeService.saveOrUpdate(vCode);
    }

    @ApiOperation("验证, 入参account其实是指的电话号码,对应v_code表的phone字段,验证完了就删了下次还能在验证")
    @DeleteMapping("/vertify")
    public Boolean vertify(String vcode) {
        QueryWrapper<VCode> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("v_cod", vcode);
        return ivCodeService.remove(queryWrapper);
    }
}
