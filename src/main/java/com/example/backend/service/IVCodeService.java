package com.example.backend.service;

import com.example.backend.VO.PhoneLoginVO;
import com.example.backend.entity.VCode;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-18
 */
public interface IVCodeService extends IService<VCode> {

    Boolean checkCode(PhoneLoginVO phoneLoginVO);
}
