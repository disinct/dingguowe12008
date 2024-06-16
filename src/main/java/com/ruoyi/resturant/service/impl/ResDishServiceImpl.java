package com.ruoyi.resturant.service.impl;

import com.ruoyi.resturant.domain.ResDish;
import com.ruoyi.resturant.mapper.ResDishMapper;
import com.ruoyi.resturant.service.IResDishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


    @Service
    public class ResDishServiceImpl implements IResDishService {
    private static final Logger log = LoggerFactory.getLogger(ResDishServiceImpl.class);
    @Autowired
    private ResDishMapper resDishMapper;

    /**
     * 查询菜品
     *
     * @param id 菜品主键
     * @return 菜品
     */
    @Override
    public ResDish selectResDishById(Long id)
    {
        return resDishMapper.selectResDishById(id);
    }

    @Override
    public List<ResDish> selectResDishByParam(String dishName) {
        return resDishMapper.selectResDishByParam(dishName);
    }

    /**
     * 新增菜品
     *
     * @param resDish 菜品
     * @return 结果
     */
    @Override
    public int insertResDish(ResDish resDish)
    {
        resDish.setCreateTime(new Date());
        return resDishMapper.insertResDish(resDish);
    }

    /**
     * 修改菜品
     *
     * @param resDish 菜品
     * @return 结果
     */
    @Override
    public int updateResDish(ResDish resDish)
    {
        resDish.setUpdateTime(new Date());
        return resDishMapper.updateResDish(resDish);
    }



    /**
     * 删除菜品
     *
     * @param id 菜品主键
     * @return 结果
     */
    @Override
    public int deleteResDishById(Long id)
    {
        return resDishMapper.deleteResDishById(id);
    }
}
