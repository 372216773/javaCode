package com.wj.project.entity.vo;

import lombok.Data;

@Data
/**
 * 前端响应包
 */
public class AddressVo {

    /**
     * 省份id
     */
    private String provinceId;

    /**
     * 市区id
     */
    private String cityId;

    /**
     * 县id
     */
    private String countyId;

    /**
     * 详情街道信息
     */
    private String detail;
}
