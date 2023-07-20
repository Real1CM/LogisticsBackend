package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value="Reservation对象", description="")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "预订订单ID")
    @TableId(value = "reservations_ID", type = IdType.AUTO)
    private Integer reservationsId;

    @ApiModelProperty(value = "医疗服务ID")
    @TableField("medical_schedule_ID")
    @JsonProperty("medicalId")
    private Integer medicalId;

    @ApiModelProperty(value = "基地预约ID")
    @TableField("visiting_schedule_ID")
    @JsonProperty("visitingId")
    private Integer visitingId;

    @ApiModelProperty(value = "用户账户")
    @TableField("account")
        private String account;


}
