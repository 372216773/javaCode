package com.wj.project.goods.entity;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

@Data
public class GoodsCart  implements Serializable {

	private static final long serialVersionUID =  4407408441702050746L;

	/**
	 * 用户id
	 */
	private String uId;

	/**
	 * 商品id
	 */
	private String gId;

	/**
	 * 用户昵称
	 */
	private String uName;

	/**
	 * 商品标题
	 */
	private String gTitle;

	/**
	 * 商品数量
	 */
	private Long gMember;

	/**
	 * 是否删除 0:false 1:true
	 */
	private Integer isDelete;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModify;

}
