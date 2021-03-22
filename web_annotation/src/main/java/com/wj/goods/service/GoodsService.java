package com.wj.goods.service;

import com.wj.goods.annotation.Service;
import com.wj.goods.dao.GoodsDao;
import com.wj.goods.dao.MemberDao;

@Service("goodsService")
public class GoodsService {
    private GoodsDao goodsDao;
    private MemberDao memberDao;
    public void remove() {
        goodsDao.delete();
    }
}
