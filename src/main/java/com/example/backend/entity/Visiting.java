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
@ApiModel(value="Visiting对象", description="")
public class Visiting implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "绿化基地ID")
    @TableId(value = "visiting_ID", type = IdType.AUTO)
    private Integer visitingId;

    @ApiModelProperty(value = "绿化基地名称")
    private String visitingname;

    @ApiModelProperty(value = "绿化基地时间")
    @TableField("vIsiting_time_ID")
    private Integer visitingTimeId;


}
