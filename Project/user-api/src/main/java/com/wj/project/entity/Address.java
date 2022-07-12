package com.wj.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Address  implements Serializable {

	private static final long serialVersionUID =  4788625460118866677L;

	/**
	 * 地址id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	/**
	 * 省份id
	 */
	private String pId;

	/**
	 * 省份名称
	 */
	private String pName;

	/**
	 * 市区id
	 */
	private String cId;

	/**
	 * 市区名称
	 */
	private String cName;

	/**
	 * 县id
	 */
	private String coId;

	/**
	 * 县名称
	 */
	private String coName;

	/**
	 * 街道详细情况
	 */
	private String detail;

	/**
	 * 用户id
	 */
	private String uId;

	/**
	 * 用户名称
	 */
	private String uName;

}
