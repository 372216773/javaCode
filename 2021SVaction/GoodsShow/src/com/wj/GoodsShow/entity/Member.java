package com.wj.GoodsShow.entity;

public class Member {
    private String nickname;
    private String sex;
    private String description;

    public Member(String nickname, String sex, String description) {
        this.nickname = nickname;
        this.sex = sex;
        this.description = description;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
