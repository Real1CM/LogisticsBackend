package com.example.backend.entity;

import lombok.Data;

@Data
public class LoginForm {
    //用户登录表单信息
    private String username;    //用户名
    private String password;    //密码
    private String verifiCode;  //验证码
    private Integer userType;   //用户类型
}
