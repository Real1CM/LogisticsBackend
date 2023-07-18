package com.example.backend.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2023-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="VCode对象", description="")
public class VCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String Id;

    @ApiModelProperty(value = "电话!")
    @TableField("phone")
    private String account;

    @ApiModelProperty(value = "验证码")
    @TableField("v_cod")
    @JsonProperty("vCod")
    private String vCod;


}
