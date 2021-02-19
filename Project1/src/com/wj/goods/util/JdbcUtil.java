package com.wj.goods.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Properties;

public class JdbcUtil {

    private static QueryRunner queryRunner = null;

    //不允许外部直接访问
    private JdbcUtil(){}

    static{
        try {
            Properties properties = new Properties();
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            queryRunner = new QueryRunner();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner(){
        return queryRunner;
    }

}
