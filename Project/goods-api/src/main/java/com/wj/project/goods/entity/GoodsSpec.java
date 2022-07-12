package com.wj.project.goods.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import lombok.Data;

//商品规格
@Data
public class GoodsSpec  implements Serializable {

	private static final long serialVersionUID =  8389830610302904270L;

	/**
	 * 商品规格id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 商品id
	 */
	private String gId;

	/**
	 * 商品标题
	 */
	private String gTitle;

}
