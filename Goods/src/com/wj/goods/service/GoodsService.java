package com.wj.goods.service;

import com.wj.goods.dao.GoodsDao;
import com.wj.goods.entity.Good;

import java.sql.SQLException;
import java.util.List;

public class GoodsService {

    public List<Good> listAll() throws SQLException {

        GoodsDao goodsDao = new GoodsDao();
        List<Good> goodList = goodsDao.listAll();

        return goodList;

    }

}
