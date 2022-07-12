package com.wj.project.goods.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Goods  implements Serializable {

	private static final long serialVersionUID =  425427793235950331L;

	/**
	 * 商品id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
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
	 * 商品原价(分)
	 */
	private Long originPrice;

	/**
	 * 商品现价(分)
	 */
	private Long currentPrice;

	/**
	 * 商品图片地址
	 */
	private String imageUrl;

	/**
	 * 商品详情
	 */
	private String detail;

	/**
	 * 商品状态 0:未上架 1:已上架
	 */
	private Integer status;

	/**
	 * 商品分类id
	 */
	private String cId;

	/**
	 * 商品库存
	 */
	private Long stock;

	/**
	 * 创建时间
	 */
	private Date gmtCreate;

	/**
	 * 修改日期
	 */
	private Date gmtModify;

	@TableField(exist = false)
	private List<String> specList;

}
