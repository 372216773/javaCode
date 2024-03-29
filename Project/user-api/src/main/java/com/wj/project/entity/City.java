package com.wj.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class City  implements Serializable {

	private static final long serialVersionUID =  6363393357946452271L;

	/**
	 * 市区id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 市区名称
	 */
	private String name;

	/**
	 * 省份id
	 */
	private String pId;

}
