package com.wj.goods.controller;

import com.wj.goods.service.MemberService;

public class MemberController {

    //默认为null
    private MemberService memberService;
    public String vipNumber;

    public void add() {
        memberService.add();
    }

}
