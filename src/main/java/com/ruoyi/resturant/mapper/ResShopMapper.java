package com.ruoyi.resturant.mapper;

import com.ruoyi.resturant.domain.ResShop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ResShopMapper {
    public  ResShop selectResShopById(Long id);

    /**
     * 查询店铺列表
     *
     * @param shopName 商铺名称
     * @return 店铺集合
     */
    public List<ResShop> selectResShopByParam (String shopName);

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
