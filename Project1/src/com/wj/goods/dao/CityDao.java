package com.wj.goods.dao;

import com.wj.goods.entity.City;
import com.wj.goods.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

//对City相关的sql语句的操作,返回数据
public class CityDao {

    public static List<City> listAll() throws Exception {
        QueryRunner queryRunner = JdbcUtil.getQueryRunner();
        List<City> query = (List<City>) queryRunner.query("select * from city where pid = ?", new BeanListHandler(City.class));
        return query;
    }
}
