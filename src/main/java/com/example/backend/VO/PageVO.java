package com.example.backend.VO;

import lombok.Data;

@Data
public class PageVO {
    private static int PAGE_SIZE = 20; //一页多少个
    private static int PAGE_NUM = 1; //开始时第几页

    public int pageSize = PAGE_SIZE;
    public int pageNum = PAGE_NUM;
    public int dataSum;
}
