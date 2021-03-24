package com.wj.goods.service;

import com.wj.goods.annotation.Qualifier;
import com.wj.goods.annotation.Service;
import com.wj.goods.dao.GoodsDao;
import com.wj.goods.dao.MemberDao;

@Service("goodsService")
public class GoodsService {

    @Qualifier
    private GoodsDao goodsDao;
    @Qualifier
    private MemberDao memberDao;
    public void remove() {
        goodsDao.delete();
    }
}
