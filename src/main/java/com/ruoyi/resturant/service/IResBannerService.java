package com.ruoyi.resturant.service;

import com.ruoyi.resturant.domain.ResBanner;

import java.util.List;

public interface IResBannerService {
    public ResBanner selectResBannerById(Long id);

    /**
     * 查询轮播图列表
     *
     * @param bannerName 轮播图名称
     * @return 轮播图集合
     */
    public List<ResBanner> selectResBannerByParam (String bannerName);

    /**
     * 新增轮播图
     *
     * @param resBanner 轮播图
     * @return 结果
     */
    public int insertResBanner(ResBanner resBanner);

    /**
     * 修改轮播图
     *
     * @param resBanner 轮播图
     * @return 结果
     */
    public int updateResBanner(ResBanner resBanner);

    /**
     * 删除轮播图信息
     *
     * @param id 轮播图主键
     * @return 结果
     */
    public int deleteResBannerById(Long id);
}
