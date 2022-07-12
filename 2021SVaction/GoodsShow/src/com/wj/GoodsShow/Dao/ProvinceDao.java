package com.wj.GoodsShow.Dao;

import com.wj.GoodsShow.Util.JdbcUtil;
import com.wj.GoodsShow.entity.Province;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;

//数据访问层
public class ProvinceDao {
    private static List<Province> list = null;

    public List<Province> listAll() {
        try {
            list = (List<Province>) JdbcUtil.getQueryRunner().query("select * from province", new Object[]{}, new BeanListHandler(Province.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
