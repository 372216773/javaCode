package com.wj.goods.service;

import com.wj.goods.annotation.Qualifier;
import com.wj.goods.annotation.Service;
import com.wj.goods.dao.MemberDao;

@Service("memberService")
public class MemberService {
    @Qualifier
    private MemberDao memberDao;

    public void add() {
        memberDao.insert();
    }
}
