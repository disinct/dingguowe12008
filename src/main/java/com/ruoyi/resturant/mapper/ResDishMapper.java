package com.ruoyi.resturant.mapper;

import com.ruoyi.resturant.domain.ResDish;
import java.util.List;

public interface ResDishMapper {
    public ResDish selectResDishById(Long id);

    /**
     * 查询菜品列表
     *
     * @param dishName 菜品名称
     * @return 菜品集合
     */
    public List<ResDish> selectResDishByParam (String dishName);

    /**
     * 新增菜品
     *
     * @param resDish 菜品
     * @return 结果
     */
    public int insertResDish(ResDish resDish);

    /**
     * 修改菜品
     *
     * @param resDish 菜品
     * @return 结果
     */
    public int updateResDish(ResDish resDish);

    /**
     * 删除菜品信息
     *
     * @param id 菜品主键
     * @return 结果
     */
    public int deleteResDishById(Long id);
}
