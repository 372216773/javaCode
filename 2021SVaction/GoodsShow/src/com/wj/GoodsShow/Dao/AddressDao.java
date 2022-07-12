package com.wj.GoodsShow.Dao;

import com.wj.GoodsShow.Util.JdbcUtil;

import java.sql.SQLException;

public class AddressDao {

    public Integer insert(Integer id, Integer pid, Integer cid, String extra) {
        Object[] params = new Object[]{id,pid,cid,extra};
        Integer result=-1;
        try {
            result = JdbcUtil.getQueryRunner().update("insert into address values(?,?,?,?)",params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
