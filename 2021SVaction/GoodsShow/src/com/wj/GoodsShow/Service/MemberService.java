package com.wj.GoodsShow.Service;

import com.wj.GoodsShow.Dao.MemberDao;
import com.wj.GoodsShow.Util.MD5Util;
import com.wj.GoodsShow.entity.Member;

import java.sql.SQLException;
import java.util.List;

public class MemberService {
    private MemberDao memberDao = new MemberDao();
    public void addUser(String nickname,String password) throws SQLException {
        memberDao.insert(nickname,password);
    }

}
