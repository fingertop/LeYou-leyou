package com.leyou.common.expojo;

import lombok.Data;

import java.util.List;

@Data
public class  PageResult<T>{

    private Long totals;
    private Integer totalPage;
    private List<T> items;

    public PageResult(Long totals, List<T> items) {
        this.totals = totals;
        this.items = items;
    }

    public PageResult(Long totals, Integer totalPage, List<T> items) {
        this.totals = totals;
        this.totalPage = totalPage;
        this.items = items;
    }
}
