package com.wj.GoodsShow.Service;

import com.wj.GoodsShow.Dao.AddressDao;
import com.wj.GoodsShow.Dao.CityDao;
import com.wj.GoodsShow.Dao.MemberDao;
import com.wj.GoodsShow.Dao.ProvinceDao;
import com.wj.GoodsShow.Util.MD5Util;

import java.sql.SQLException;

public class RegistService {

    private MemberDao memberDao = new MemberDao();
    private AddressDao addressDao = new AddressDao();

    public void regist(String nickname, String password, Integer pid, Integer cid, String extra) throws SQLException {
        //加密
        password = MD5Util.getMD5String(password);
        //1.加到Member表中
        memberDao.insert(nickname,password);

        //2.查询到最后一次自增的id,加到address表中
        Integer lastId = memberDao.selectLastId();
        addressDao.insert(lastId,pid,cid,extra);

    }
}
