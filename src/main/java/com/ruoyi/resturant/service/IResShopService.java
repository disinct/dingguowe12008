package com.ruoyi.resturant.service;

import com.ruoyi.resturant.domain.ResShop;

import java.util.List;

/**
 * 店铺Service接口
 *
 * @author Daniel
 * @date 2023-09-27
 */
public interface IResShopService
{
    /**
     * 查询店铺
     *
     * @param id 店铺主键
     * @return 店铺
     */
    public ResShop selectResShopById(Long id);

    /**
     * 查询店铺列表
     *
     * @param shopName 商铺名称
     * @return 店铺集合
     */
    public List<ResShop> selectResShopByParam(String shopName);


    /**
     * 新增店铺
     *
     * @param resShop 店铺
     * @return 结果
     */
    public int insertResShop(ResShop resShop);

    /**
     * 修改店铺
     *
     * @param resShop 店铺
     * @return 结果
     */
    public int updateResShop(ResShop resShop);



    /**
     * 删除店铺信息
     *
     * @param id 店铺主键
     * @return 结果
     */
    public int deleteResShopById(Long id);
}
