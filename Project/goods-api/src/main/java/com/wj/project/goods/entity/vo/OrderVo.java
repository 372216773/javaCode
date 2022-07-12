package com.wj.project.goods.entity.vo;

import lombok.Data;

@Data
public class OrderVo {

    //不传递价格,可能会被抓包

    /**
     * 商品id
     */
    private String goodsId;

    /**
     * 地址id
     */
    private String addrId;

    /**
     * 购买数量
     */
    private Long number;


}
