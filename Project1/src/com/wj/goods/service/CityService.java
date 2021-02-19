package com.wj.goods.service;

import com.wj.goods.dao.CityDao;
import com.wj.goods.entity.City;

import java.util.List;

//包含对City的操作方法
public class CityService {

    public static List<City> listByPid() throws Exception {
        List<City> cityList = CityDao.listAll();
        return cityList;
    }

}

