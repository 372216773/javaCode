package com.wj.GoodsShow.service;

import com.wj.GoodsShow.Service.CityService;
import com.wj.GoodsShow.entity.City;
import org.junit.Test;

import java.util.List;
import java.util.Scanner;

public class CityServiceTest {

    @Test
    public void testListByPid() {
        CityService cityService = new CityService();
        List<City> list = cityService.listByPid(2);
        System.out.println(list);
    }
}
