package com.wj.project.goods.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wj.common.entity.PageResponse;
import com.wj.common.entity.Response;
import com.wj.common.entity.ResponseCode;
import com.wj.project.goods.entity.Goods;
import com.wj.project.goods.entity.GoodsSpec;
import com.wj.project.goods.mapper.GoodsMapper;
import com.wj.project.goods.mapper.GoodsSpecMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    //牵扯到两张表,使用事务保证出现一致的情况
    @Transactional
    public Response save(MultipartFile multipartFile,Goods goods) throws IOException {
        //文件上传(企业里面会有专门的资源服务器来进行存储, 或者使用云服务器存储oss)

        //获得原始文件名称
        String originalFilename = multipartFile.getOriginalFilename();
        //为文件添加UUID(全局唯一)
        String filename = UUID.randomUUID().toString().replace("-","")+"_"+originalFilename;
        //若没有文件夹,就会创建一个文件夹
        File toFile = new File("d:/upload");
        if (!toFile.exists()) {
            toFile.mkdirs();
        }
        /*getPath()会去掉多余斜杠*/
        //将文件转存到某个文件夹下
        multipartFile.transferTo(new File(toFile.getPath()+"/"+filename));

        //将文件名设置为修改后的文件名
        goods.setImageUrl(filename);
        //添加商品
        goodsMapper.insert(goods);

        //获取商品规格
        List<String> specList = goods.getSpecList();

        specList.forEach((title)->{
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setGId(goods.getId());
            goodsSpec.setGTitle(goods.getTitle());
            goodsSpec.setTitle(title);
            goodsSpecMapper.insert(goodsSpec);
        });

        //配置信息
        Response response = new Response();
        response.code(ResponseCode.SUCCESS).message("添加商品成功");

        return response;
    }

    public PageResponse listByGoods(Long current,Long size, Goods goods){

        //对数据进行分页
        IPage<Goods> page = new Page<>(current,size);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>(goods);
        page = goodsMapper.selectPage(page,queryWrapper);
        PageResponse pageResponse = new PageResponse();
        pageResponse.code(ResponseCode.SUCCESS).message("获取数据成功").data(page.getRecords());
        pageResponse.current(current).size(size).total(page.getTotal());
        return pageResponse;
    }


    public PageResponse listGoodsByCategory(Long current, Long size, String cId) {
        //Page类型的数据包括 当前页数,每页大小,符合条件的所有数据总数,,当前页数据
        IPage<Goods> page = new Page<>(current,size);
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        //这里的列对应的是表中得列名,不是类的属性
        queryWrapper.eq("c_Id",cId);
        //两个参数 1:分页情况 2:数据样板
        //为page赋值为查询到的数据
        page = goodsMapper.selectPage(page,queryWrapper);
        PageResponse pageResponse = new PageResponse();
        pageResponse.code(ResponseCode.SUCCESS).message("此类别的数据获取成功").data(page.getRecords());
        pageResponse.current(current).size(size).total(page.getTotal());

        return pageResponse;
    }

    /**
     *
     * @param title 商品标题
     * @return 商品列表
     */
    public PageResponse listByTitleWithNgram(String title,Long current,Long size) {
        Long start = (current - 1) * size;
        List<Goods> goodsList = goodsMapper.listByTitleWithNgram(title, start, size);
        Long total = goodsMapper.totalByTitleWithNgram(title);
        PageResponse pageResponse = new PageResponse();
        pageResponse.code(ResponseCode.SUCCESS).message("数据获取成功").data(goodsList);
        pageResponse.current(current).size(size).total(total);
        return pageResponse;
    }

    /**
     * 获取查询到的数据的总条数
     * @param title 商品标题
     * @return 总条数
     */
    public Long totalByTitleWithNgram(String title) {
        Long total = goodsMapper.totalByTitleWithNgram(title);
        return total;
    }
}
