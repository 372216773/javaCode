package com.wj.goods.dao;

import com.wj.goods.annotation.Repository;

@Repository("memberDao")
public class MemberDao {
    public void insert() {
        System.out.println("memberDao的insert()");
    }
}
