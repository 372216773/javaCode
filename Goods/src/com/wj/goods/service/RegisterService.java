package com.wj.goods.service;

import com.wj.goods.dao.MemberDao;

import java.sql.SQLException;

public class RegisterService {

    public void register(String nickname,String password) throws SQLException {

        MemberDao memberDao = new MemberDao();
        memberDao.insert(nickname, password);

    }

}
