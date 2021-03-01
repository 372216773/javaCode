package com.wj.goods.service;

import com.wj.goods.dao.GoodsDao;
import com.wj.goods.dao.MemberDao;

public class MemberService {

    private MemberDao memberDao;
    private GoodsDao goodsDao;

    public void add() {

        memberDao.insert();

    }
}
