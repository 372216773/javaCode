package com.wj.goods.dao;

import com.wj.goods.util.JdbcUtil;

import java.sql.SQLException;

public class MemberDao {

    public int insert(String nickname,String password) throws SQLException {

        Object[] params = new Object[]{nickname,password};
        //添加数据,返回更新的行数
        int update = JdbcUtil.getQueryRunner().update("insert into member(nickname,password) values(?,?)", params);

        //返回0,即没有添加成功
        return update;
    }

}
