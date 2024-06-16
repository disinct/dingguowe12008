package com.ruoyi.resturant.service.impl;


import com.ruoyi.resturant.domain.ResShop;
import com.ruoyi.resturant.mapper.ResShopMapper;
import com.ruoyi.resturant.service.IResShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 店铺Service业务层处理
 *
 * @author Daniel
 * @date 2023-09-27
 */
@Service
public class ResShopServiceImpl implements IResShopService
{
    private static final Logger log = LoggerFactory.getLogger(ResShopServiceImpl.class);
    @Autowired
    private ResShopMapper resShopMapper;

    /**
     * 查询店铺
     *
     * @param id 店铺主键
     * @return 店铺
     */
    @Override
    public ResShop selectResShopById(Long id)
    {
        return resShopMapper.selectResShopById(id);
    }

    @Override
    public List<ResShop> selectResShopByParam(String shopName) {
        return resShopMapper.selectResShopByParam(shopName);
    }

    /**
     * 新增店铺
     *
     * @param resShop 店铺
     * @return 结果
     */
    @Override
    public int insertResShop(ResShop resShop)
    {
        return resShopMapper.insertResShop(resShop);
    }

    /**
     * 修改店铺
     *
     * @param resShop 店铺
     * @return 结果
     */
    @Override
    public int updateResShop(ResShop resShop)
    {
        return resShopMapper.updateResShop(resShop);
    }



    /**
     * 删除店铺信息
     *
     * @param id 店铺主键
     * @return 结果
     */
    @Override
    public int deleteResShopById(Long id)
    {
        return resShopMapper.deleteResShopById(id);
    }
}


