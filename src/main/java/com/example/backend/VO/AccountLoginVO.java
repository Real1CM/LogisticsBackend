package com.example.backend.VO;

import lombok.Data;

@Data
public class AccountLoginVO {
    private String account;
    private String pswd;
    private int status; //1管理员 0普通用户
}
