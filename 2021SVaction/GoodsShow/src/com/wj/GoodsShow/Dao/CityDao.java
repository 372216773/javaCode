package com.wj.GoodsShow.Dao;

import com.wj.GoodsShow.Util.JdbcUtil;
import com.wj.GoodsShow.entity.City;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CityDao {

    private List<City> list = null;

    public List<City> listByPid(int pid) {

        Object[] params = new Object[]{pid};
        try {
            list = (List<City>) JdbcUtil.getQueryRunner().query("select * from city where pid = ?", params, new BeanListHandler(City.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
