package com.wj.GoodsShow.Service;


import com.wj.GoodsShow.Dao.ProvinceDao;
import com.wj.GoodsShow.Util.JdbcUtil;
import com.wj.GoodsShow.entity.Province;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class ProvinceService {

    ProvinceDao provinceDao = new ProvinceDao();

    /**
     * 查询所有省份
     *
     * @return
     */
    public List<Province> listAll() {
        return provinceDao.listAll();
    }

}
