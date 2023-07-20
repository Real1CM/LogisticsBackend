package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liang-chenming
 * @since 2023-07-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Medical对象", description="")
public class Medical implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "医疗ID")
    @TableId(value = "medical_ID", type = IdType.AUTO)
    private Integer medicalId;

    @ApiModelProperty(value = "医疗服务名")
    private String medicalName;

    @ApiModelProperty(value = "医疗服务时间ID")
    @TableField("medical_schedule_ID")
    private Integer medicalScheduleId;


}
