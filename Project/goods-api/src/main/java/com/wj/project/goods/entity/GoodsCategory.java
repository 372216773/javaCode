package com.wj.project.goods.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class GoodsCategory  implements Serializable {

	private static final long serialVersionUID =  6667949746859220224L;

	/**
	 * 商品类别
	 */

	//全局唯一id的字符串形式
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 类别标题
	 */
	private String title;

	/**
	 * 类别描述
	 */
	private String description;

}
