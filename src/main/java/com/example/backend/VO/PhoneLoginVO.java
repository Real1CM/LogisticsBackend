package com.example.backend.VO;

import lombok.Data;

@Data
public class PhoneLoginVO {
    private String Phone;
    private String vCode;
    private String status = "成功";

}
