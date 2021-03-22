package com.wj.goods.dao;

import com.wj.goods.annotation.Repository;

@Repository("goodsDao")
public class GoodsDao {
    public void delete() {
        System.out.println("goodsDao的remove()");
    }

    public void insert( ) {
        System.out.println("goodsDao的insert()");
    }
}
