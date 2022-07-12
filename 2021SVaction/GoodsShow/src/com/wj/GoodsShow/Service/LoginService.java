package com.wj.GoodsShow.Service;

import com.wj.GoodsShow.Dao.MemberDao;
import com.wj.GoodsShow.Util.MD5Util;

import java.sql.SQLException;

public class LoginService {
    MemberDao memberDao = new MemberDao();

    public String login(String nickname, String password) throws SQLException {

        Long count = memberDao.countByNickname(nickname, MD5Util.getMD5String(password));
        if (count > 0) {
            return nickname;
        }
        return null;
    }
}
