package com.example.backend.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer i; //状态码,0是正常
    private T data;

}
