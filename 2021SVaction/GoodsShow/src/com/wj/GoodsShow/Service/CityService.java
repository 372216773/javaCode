package com.wj.GoodsShow.Service;

import com.wj.GoodsShow.Dao.CityDao;
import com.wj.GoodsShow.Util.JdbcUtil;
import com.wj.GoodsShow.entity.City;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CityService {

    private CityDao cityDao = new CityDao();

    public List<City> listByPid(int pid) {
        return cityDao.listByPid(pid);
    }
}
