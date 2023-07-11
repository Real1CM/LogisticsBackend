package com.example.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String pswd;

    @ApiModelProperty(value = "1:男,2:女")
    private String gender;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "管理员0，其他用户1")
    private String status;

    @ApiModelProperty(value = "图片网站")
    private String picture;
}
