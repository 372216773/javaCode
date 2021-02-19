package com.wj.goods.dao;

import com.wj.goods.entity.Province;
import com.wj.goods.util.JdbcUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

////对City相关的sql语句的操作,返回数据
public class ProvinceDao {

    public static List<Province> listAll() throws Exception {
        QueryRunner queryRunner = JdbcUtil.getQueryRunner();
        List<Province> query = (List<Province>) queryRunner.query("select * from Province", new BeanListHandler(Province.class));
        return query;
    }

}
