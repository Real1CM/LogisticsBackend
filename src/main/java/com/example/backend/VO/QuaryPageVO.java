package com.example.backend.VO;

import lombok.Data;

import java.util.HashMap;

//分页
@Data
public class QuaryPageVO {

    private static int PAGE_SIZE = 20; //一页多少个
    private static int PAGE_NUM = 1; //开始时第几页

    public int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;

    private HashMap map;
}
