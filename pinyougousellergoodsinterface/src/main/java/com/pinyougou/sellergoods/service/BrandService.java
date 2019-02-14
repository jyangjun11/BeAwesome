package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 品牌业务接口
 */
public interface BrandService {

    /**
     * 查询品牌列表
     * @return
     */
    public List<TbBrand> findAll();

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage(int pageNum,int pageSize);


    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult findPage( TbBrand brand, int pageNum,int pageSize);

    /**
     * 增加
     * @param brand
     */
    public void add(TbBrand brand);

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public TbBrand findOne(Long id);

    /**
     * 修改
     * @param brand
     */
    public  void update(TbBrand brand);


    /**
     * 删除
     * @param ids
     */
    public  void delete(Long []ids);

    /**
     * 下拉列表数据
     * @return
     */
    public List<Map> selectOptionList();
}
