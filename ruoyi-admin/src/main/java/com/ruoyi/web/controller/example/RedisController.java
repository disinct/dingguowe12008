package com.ruoyi.web.controller.example;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.resturant.domain.ResShop;
import com.ruoyi.resturant.service.IResShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(tags = "Redis测试")
@Anonymous
@RestController
@RequestMapping("/api/redis")
public class RedisController {
    @Autowired
    private IResShopService resShopService;
    @Autowired
    private RedisCache redisCache;
    //根据店铺ID取得店铺详情，同时把店铺对象存到redis中

    @ApiOperation("根据店铺ID取得店铺详情，同时把店铺对象存到Redis中")
    @ApiImplicitParam(name = "id", value = "店铺ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)
    @GetMapping("/{id}")
    public AjaxResult setRedisObj(@PathVariable("id") long id){
        //1根据店铺id取得店铺详情
        ResShop resShop = resShopService.selectResShopById(id);
        //2店铺对象resShop存到redis中
        String str= JSON.toJSONString(resShop);
        redisCache.setCacheObject(String.valueOf(id), str, 1, TimeUnit.HOURS);
        //返回店铺对象
        return  AjaxResult.success(resShop);
    }
    @ApiOperation("根据店铺ID取得Redis中店铺对象")
    @ApiImplicitParam(name = "id", value = "店铺ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)
    @GetMapping("/ids/{id}")
    public AjaxResult getRedisObj(@PathVariable("id") long id){
        // 1 根据店铺ID从Redis中取得店铺JSON字符串
        String str = redisCache.getCacheObject(String.valueOf(id));
        // 2 把店铺的JSON字符串转成店铺对象ResShop
        ResShop resShop = JSON.parseObject(str, ResShop.class);

        return AjaxResult.success(resShop);
    }


}
