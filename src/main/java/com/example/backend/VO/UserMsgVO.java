package com.example.backend.VO;

import lombok.Data;

@Data
public class UserMsgVO {
    private String phone;
    private String code; //验证码
}
