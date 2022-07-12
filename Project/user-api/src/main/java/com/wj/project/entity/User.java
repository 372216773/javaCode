package com.wj.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User  implements Serializable {

	private static final long serialVersionUID =  463068865115253149L;

	/**
	 * 主键
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 管理员昵称
	 */
	private String nickname;

	/**
	 * 管理员密码
	 */
	private String password;

	/**
	 * 管理员头像
	 */
	private String avatar;

}
