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
@ApiModel(value="GoodsDetail对象", description="")
public class GoodsDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品明细ID")
    @TableId(value = "goods_detail_ID", type = IdType.AUTO)
    private Integer goodsDetailId;

    @ApiModelProperty(value = "用户账户")
    @TableField("account")
    private Integer account;

    @ApiModelProperty(value = "商品ID")
    @TableField("goods_ID")
    private Integer goodsId;

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "1:已送达,2:正在配送,3:正在出餐")
    private String status;


}
