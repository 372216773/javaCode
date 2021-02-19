package com.wj.goods.service;

import com.wj.goods.dao.ProvinceDao;
import com.wj.goods.entity.Province;

import java.util.List;

//包含对于Province的操作方法
public class ProvinceService {

    public static List<Province> listAll() throws Exception {
        List<Province> provinceList = ProvinceDao.listAll();
        return provinceList;
    }

}

