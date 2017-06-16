package com.crm4j.base.util;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/8
 */
public class PageDto {

    private Integer draw;//操作次数
    private List data;//数据集
    private Long recordsTotal;//数据总条数
    private Long recordsFiltered;//需要显示的条数

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
