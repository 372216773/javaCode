package com.wj.GoodsShow.service;

import com.wj.GoodsShow.Dao.MemberDao;
import com.wj.GoodsShow.Service.LoginService;
import com.wj.GoodsShow.Service.RegistService;
import com.wj.GoodsShow.entity.Member;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class RegistServiceTest {

    MemberDao memberDao = new MemberDao();

    @Test
    public void testCheck() throws SQLException {
        RegistService registService = new RegistService();
        registService.regist("wj", "123", 1, 2, "shanxi");
    }

    @Test
    public void testLogin() throws SQLException {
        LoginService loginService = new LoginService();
        String login = loginService.login("root", "root");
        System.out.println(login);
    }
}
