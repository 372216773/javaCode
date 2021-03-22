package com.wj.goods.controller;

import com.wj.goods.annotation.Controller;
import com.wj.goods.service.MemberService;

@Controller("memberController")
public class MemberController {

    private MemberService memberService;
    private String vipName;

    public void add() {
        memberService.add();
    }

}
