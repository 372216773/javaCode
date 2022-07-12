package com.wj.GoodsShow.Dao;

import com.wj.GoodsShow.Util.JdbcUtil;
import com.wj.GoodsShow.entity.Goods;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class GoodsDao {

    /**
     * 返回所有商品
     *
     * @return 商品表
     * @throws SQLException
     */
    public List<Goods> listAll() throws SQLException {
        return (List<Goods>) JdbcUtil.getQueryRunner().query("select * from goods", new BeanListHandler(Goods.class));
    }

    /**
     * @param start  开始位置
     * @param length 每页大小
     * @return 每页商品
     * @throws SQLException
     */
    public List<Goods> selectAll(Integer start, Integer length) throws SQLException {
        Object[] params = new Object[]{start, length};
        return (List<Goods>) JdbcUtil.getQueryRunner().query("select * from goods limit ?,?", params, new BeanListHandler(Goods.class));
    }

    /**
     * 总页数
     * @return 总页数
     * @throws SQLException
     */
    public Integer countNum() throws SQLException {
        long result = (long) JdbcUtil.getQueryRunner().query("select count(*) from goods", new ScalarHandler());
        return (int) result;
    }

    public String deleteById(String id) throws SQLException {
        Object[] params=new Object[]{id};
        return String.valueOf(JdbcUtil.getQueryRunner().update("delete from goods where id = ?",params));
    }

    public Integer addGoods(String id, String title,  String description,long price, String image) throws SQLException {
        Object[] params=new Object[]{id,title,description,price,image};
        return JdbcUtil.getQueryRunner().update("insert into goods(id,title,description,price,image) values(?,?,?,?,?)", params);
    }

    public Goods getGood(String id) throws SQLException {
        Object[] params = new Object[]{id};
        return (Goods) JdbcUtil.getQueryRunner().query("select * from goods where id = ?", params, new BeanHandler(Goods.class));
    }

    public Integer replaceGood(String id, String title, String description, long price, String image) throws SQLException {
        Object[] params=new Object[]{title,description,price,image,id};
        return JdbcUtil.getQueryRunner().update("update goods set title = ?,description=?,price=?,image=? where id=?",params);

    }
}
