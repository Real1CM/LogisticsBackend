package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
@ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
    @TableId(value = "order_ID", type = IdType.AUTO)
    private Integer orderId;

    @ApiModelProperty(value = "用户ID")
    @TableField("account")
    private Integer account;

    @ApiModelProperty(value = "菜品明细ID")
    @TableField("dish_detail_ID")
    private Integer dishDetailId;

    @ApiModelProperty(value = "商品明细ID")
    @TableField("goods_detail_ID")
    @JsonProperty("goodsDetailId")
    private Integer goodsDetailId;

    @ApiModelProperty(value = "总金额")
    @TableField("money")
    private float money;

    @ApiModelProperty(value = "订单日期")
    @TableField("order_date")
    private LocalDate orderDate;


}
