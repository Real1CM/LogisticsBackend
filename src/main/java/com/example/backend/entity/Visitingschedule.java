package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
@ApiModel(value="Visitingschedule对象", description="")
public class Visitingschedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "基地时间ID")
    @TableId(value = "visiting_schedule_ID", type = IdType.AUTO)
    private Integer visitingScheduleId;

    @ApiModelProperty(value = "基地ID")
    @TableField("visiting_ID")
    private String visitingId;

    @ApiModelProperty(value = "基地时间")
    @TableField("time")
    private LocalDate time;

    @ApiModelProperty(value = "电话号码")
    @TableField("phone")
    private String phone;

}
