package com.wj.GoodsShow.Dao;

import com.wj.GoodsShow.Util.JdbcUtil;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.SQLException;

public class MemberDao {


    public Integer insert(String nickname, String password) throws SQLException {
        Object[] params = new Object[]{null, nickname, password};
        Integer result = -1;
            result = JdbcUtil.getQueryRunner().update("insert into member values (?,?,?)", params);
        return result;
    }

    public Long countByNickname(String nickname, String password) throws SQLException {
        //通过nickname读取到nickname对应的数据
        Object[] params = new Object[]{nickname, password};
        return (Long) JdbcUtil.getQueryRunner().query("select count(*) from member where nickname = ? and password = ?", params, new ScalarHandler());
    }

    public Integer selectLastId() throws SQLException {
        BigInteger result = null;
            result = (BigInteger) JdbcUtil.getQueryRunner().query("select last_insert_id()", new ScalarHandler());
        return result.intValue();
    }
}
