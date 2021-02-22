package com.wj.goods.dao;

import com.wj.goods.entity.Good;
import com.wj.goods.util.JdbcUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDao {

    public List<Good> listAll() throws SQLException {

        List<Good> goodList = (List<Good>) JdbcUtil.getQueryRunner().query("select * from goods", new BeanListHandler(Good.class));

        return goodList;
    }

}
