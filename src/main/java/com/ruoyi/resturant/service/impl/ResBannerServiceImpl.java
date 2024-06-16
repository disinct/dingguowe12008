package com.ruoyi.resturant.service.impl;

import com.ruoyi.resturant.domain.ResBanner;
import com.ruoyi.resturant.mapper.ResBannerMapper;
import com.ruoyi.resturant.service.IResBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResBannerServiceImpl implements IResBannerService {
    private static final Logger log = LoggerFactory.getLogger(ResBannerServiceImpl.class);
    @Autowired
    private ResBannerMapper resBannerMapper;

    /**
     * 查询轮播图
     *
     * @param id 轮播图主键
     * @return 轮播图
     */
    @Override
    public ResBanner selectResBannerById(Long id)
    {
        return resBannerMapper.selectResBannerById(id);
    }

    @Override
    public List<ResBanner> selectResBannerByParam(String bannerName) {
        return resBannerMapper.selectResBannerByParam(bannerName);
    }

    /**
     * 新增轮播图
     *
     * @param resBanner 轮播图
     * @return 结果
     */
    @Override
    public int insertResBanner(ResBanner resBanner)
    {
        resBanner.setCreateTime(new Date());
        return resBannerMapper.insertResBanner(resBanner);
    }

    /**
     * 修改轮播图
     *
     * @param resBanner 轮播图
     * @return 结果
     */
    @Override
    public int updateResBanner(ResBanner resBanner)
    {
        resBanner.setUpdateTime(new Date());
        return resBannerMapper.updateResBanner(resBanner);
    }



    /**
     * 删除轮播图
     *
     * @param id 轮播图主键
     * @return 结果
     */
    @Override
    public int deleteResBannerById(Long id)
    {
        return resBannerMapper.deleteResBannerById(id);
    }
}
