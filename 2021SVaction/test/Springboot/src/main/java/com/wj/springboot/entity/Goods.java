package com.wj.springboot.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
/*无参构造器*/
@NoArgsConstructor
public class Goods  implements Serializable {

	private static final long serialVersionUID =  5970768575344749083L;

	/**
	 * 主键
	 */
   
	private String id;

	/**
	 * 商品标题
	 */
	private String title;

	/**
	 * 商品描述
	 */
	private String description;

	/**
	 * 原价(分)
	 */
	private Long originPrice;

	/**
	 * vip价格(分)
	 */
	private Long vipPrice;

	/**
	 * 商品图片地址
	 */
	private String imageUrl;

}
