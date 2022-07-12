package com.wj.project.goods.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;

import java.util.Date;

@Data
public class GoodsOrder  implements Serializable {

	private static final long serialVersionUID =  7132914068421044676L;

	/**
	 * 订单id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 用户id
	 */
	private String uId;

	/**
	 * 用户名
	 */
	private String uName;

	/**
	 * 商品id
	 */
	private String gId;

	/**
	 * 商品名称
	 */
	private String gTitle;

	/**
	 * 商品数量
	 */
	private Long number;

	/**
	 * 商品单价
	 */
	private Long currentPrice;

	/**
	 * 订单总价
	 */
	private Long totalPrice;

	/**
	 * 收货地址id
	 */
	private String addrId;

	/**
	 * 订单状态 0:未支付 1:已支付
	 */
	private Integer status;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改时间
	 */
	private Date gmtModify;

}
