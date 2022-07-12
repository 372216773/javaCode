package com.wj.GoodsShow.Util;
/*
单例模式中饿汉模式,设置全局唯一的queryRunner
 */
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.wj.GoodsShow.entity.Province;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.util.Properties;

public class JdbcUtil {

    private static DataSource dataSource=null;
    private static QueryRunner queryRunner=null;

    private static Properties properties = new Properties();

    private JdbcUtil(){}

    static{
        try {
            properties.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            queryRunner = new QueryRunner(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("load error!!!");
        }
    }

    public static QueryRunner getQueryRunner() {
        return queryRunner;
    }
}
