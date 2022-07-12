package com.wj.project.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Province  implements Serializable {

	private static final long serialVersionUID =  228371849095970925L;

	/**
	 * 省份id
	 */

	@TableId(type = IdType.ID_WORKER_STR)
	private String id;

	private String name;

}
