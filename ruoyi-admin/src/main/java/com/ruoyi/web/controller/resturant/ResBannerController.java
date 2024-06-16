package com.ruoyi.web.controller.resturant;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BizException;
import com.ruoyi.resturant.domain.ResBanner;
import com.ruoyi.resturant.service.IResBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

@Anonymous
@RestController
@RequestMapping("/api/resturant/banner")
@Api(tags="轮播图管理")
public class ResBannerController extends BaseController {
    @Autowired
    private IResBannerService resBannerService;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询轮播图列表
     */
    @ApiOperation("查询轮播图列表")
    @ApiImplicitParam(name = "name", value = "轮播图名称",
            dataType = "string", dataTypeClass = String.class)
    @GetMapping("/list")
    public AjaxResult list(@RequestParam(value = "name", required = false) String bannerName) {
        List<ResBanner> list = resBannerService.selectResBannerByParam(bannerName);
        return AjaxResult.success(list);
    }

    /**
     * 获取轮播图详细信息
     */
    @ApiOperation(value = "获取轮播图详细信息", notes = "TODO：取得某个轮播图的详细信息")
    @ApiImplicitParam(name = "id", value = "轮播图ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        //1根据轮播图id取得轮播图详情
        ResBanner resBanner = resBannerService.selectResBannerById(id);
        if (resBanner == null){
            throw new BizException(404,"获取轮播图失败");
        }
        //2轮播图对象resBanner存到redis中
        String str= JSON.toJSONString(resBanner);
        redisCache.setCacheObject(String.valueOf(id), str, 1, TimeUnit.HOURS);
        //返回轮播图对象
        return AjaxResult.success(resBanner);
    }

    /**
     * 新增轮播图
     */
    @ApiOperation("新增轮播图")
    @PostMapping
    @Log(title = "轮播图管理",businessType = BusinessType.INSERT)
    public AjaxResult add(@Validated @RequestBody ResBanner resBanner) {
        return AjaxResult.success(resBannerService.insertResBanner(resBanner));
    }

    /**
     * 修改轮播图
     */
    @ApiOperation("修改轮播图")
    @PutMapping
    @Log(title = "轮播图管理",businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody ResBanner resBanner) {
        //1线上数据库查看轮播图是否存在
        ResBanner resBannerDb = resBannerService.selectResBannerById(resBanner.getId());
        //2判断轮播图是否存在，不存在则业务异常
        if (resBannerDb == null){
            throw new BizException(404,"轮播图不存在");
        }
        //3轮播图存在执行更新操作
        int num = resBannerService.updateResBanner(resBanner);
        return AjaxResult.success(num);
    }

    /**
     * 删除轮播图
     */
    @ApiOperation("删除轮播图")
    @ApiImplicitParam(name = "id", value = "轮播图ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    @Log(title = "轮播图管理",businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long id) {
        // 根据轮播图ID查询数据库中是否存在
        ResBanner resBanner = resBannerService.selectResBannerById(id);
        // 不存在的话，抛出异常BizException
        if(resBanner == null) {
            throw new BizException(404, "该轮播图id" + id + "不存在");
        }
        log.info("该轮播图id" + id + "存在");
        return AjaxResult.success(resBannerService.deleteResBannerById(id));
    }
}
