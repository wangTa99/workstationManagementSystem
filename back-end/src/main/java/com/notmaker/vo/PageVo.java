package com.notmaker.vo;

import java.io.Serializable;
import java.util.List;

public class PageVo<T> implements Serializable {
    private List<T> list;
    private Long total;
    private Integer page;
    private Integer size;

    public List<T> getList() { return list; }
    public void setList(List<T> list) { this.list = list; }

    public Long getTotal() { return total; }
    public void setTotal(Long total) { this.total = total; }

    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }

    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
}
