package com.wj.project.service;

import com.wj.project.entity.*;
import com.wj.project.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProvinceMapper provinceMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountyMapper countryMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Transactional
    public Integer save(String userId, String pId, String cId, String coId, String detailAddress) {

        User user = userMapper.selectById(userId);
        Province province = provinceMapper.selectById(pId);
        City city = cityMapper.selectById(cId);
        County county = countryMapper.selectById(coId);

        Address address = new Address();

        address.setPId(pId);
        address.setPName(province.getName());

        address.setCId(cId);
        address.setCName(city.getName());

        address.setCoId(coId);
        address.setCoName(county.getName());

        address.setDetail(detailAddress);

        address.setUId(userId);
        address.setUName(user.getNickname());

        int insert = addressMapper.insert(address);

        return insert;
    }
}
