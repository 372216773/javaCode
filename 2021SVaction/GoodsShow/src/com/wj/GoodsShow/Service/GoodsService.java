package com.wj.GoodsShow.Service;

import com.wj.GoodsShow.Dao.GoodsDao;
import com.wj.GoodsShow.entity.Goods;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class GoodsService {
    private GoodsDao goodsDao = new GoodsDao();
    static int num;

    /**
     * 所有商品
     *
     * @return
     * @throws SQLException
     */
    public List<Goods> listAll() throws SQLException {
        return goodsDao.listAll();
    }

    /**
     * 返回当前页的所有商品
     *
     * @param current 当前页码
     * @param length  每页条数
     * @return 返回当前页的所有商品
     */
    public List<Goods> pageAll(Integer current, Integer length) throws SQLException {
        Integer start = (current - 1) * length;
        //配合limit使用,就需要start,length
        List<Goods> goodsList = goodsDao.selectAll(start, length);
        num = goodsList.size();
        return goodsList;
    }

    public Integer currentNum() {
        return num;
    }

    /**
     * 商品总数
     *
     * @return
     * @throws SQLException
     */
    public Integer countNum() throws SQLException {
        return goodsDao.countNum();
    }

    /**
     * 删除商品
     *
     * @param id id
     * @return 受影响的行数
     * @throws SQLException
     */
    public String deleteById(String id) throws SQLException {
        return goodsDao.deleteById(id);
    }

    public Integer addGoods(String title, String description, long price, String image) throws SQLException {
        String id = UUID.randomUUID().toString().split("-")[0];
        price = price * 100;
        return goodsDao.addGoods(id, title, description, price, image);
    }

    public Goods getGood(String id) throws SQLException {
        return goodsDao.getGood(id);
    }

    public Integer replaceGood(String id, String title, String description, long price, String image) throws SQLException {
        price = price * 100;
        return goodsDao.replaceGood(id, title, description, price, image);
    }
}
