package com.wj.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*生成get/set方法,无参构造器*/
@Data
/*全参构造器,单无参构造器就会被顶掉,需要手动添加无参构造器*/
@AllArgsConstructor
/*无参构造器*/
@NoArgsConstructor
/*日志*/
@Slf4j
public class User {

    private String id;
    private String nickname;
    private String password;

    public void say() {
        log.error("error............");
    }

}
