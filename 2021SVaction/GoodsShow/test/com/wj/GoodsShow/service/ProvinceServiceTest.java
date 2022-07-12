package com.wj.GoodsShow.service;

import com.wj.GoodsShow.Service.ProvinceService;
import com.wj.GoodsShow.entity.Province;
import org.junit.Test;

import java.util.List;

public class ProvinceServiceTest {

    @Test
    public void testListAll(){
        ProvinceService provinceService = new ProvinceService();
        List<Province> list = provinceService.listAll();
        System.out.println(list);
    }
}
