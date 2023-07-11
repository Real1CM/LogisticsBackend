package com.example.backend.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer i; //状态码,1是管理员,0是正常,-1没有注册,-2密码不对
    private T data;

}
