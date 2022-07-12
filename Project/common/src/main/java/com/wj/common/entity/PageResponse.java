package com.wj.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponse extends Response {

    //当前页码
    private Long current;

    //每页大小
    private Long size;

    //总条数
    private Long total;

    public PageResponse current(Long current) {
        this.current = current;
        return this;
    }

    public PageResponse size(Long size) {
        this.size = size;
        return this;
    }

    public PageResponse total(Long total) {
        this.total = total;
        return this;
    }
}
