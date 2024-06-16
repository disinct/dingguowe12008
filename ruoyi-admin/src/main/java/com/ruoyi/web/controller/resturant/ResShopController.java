package com.ruoyi.web.controller.resturant;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BizException;
import com.ruoyi.resturant.domain.ResShop;
import com.ruoyi.resturant.mapper.ResShopMapper;
import com.ruoyi.resturant.service.IResShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.ruoyi.framework.datasource.DynamicDataSourceContextHolder.log;

/**
 * 店铺Controller
 *
 * @author Daniel
 * @date 2023-09-27
 */
@Anonymous
@RestController
@RequestMapping("/api/resturant/shop")
@Api(tags="店铺管理")
public class ResShopController extends BaseController {
    @Autowired
    private IResShopService resShopService;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询店铺列表
     */
    @ApiOperation("查询店铺列表")
    @ApiImplicitParam(name = "name", value = "店铺名称",
            dataType = "string", dataTypeClass = String.class)
    @GetMapping("/list")
    public AjaxResult list(@RequestParam(value = "name", required = false) String shopName) {
        List<ResShop> list = resShopService.selectResShopByParam(shopName);
        return AjaxResult.success(list);
    }

    /**
     * 获取店铺详细信息
     */
    @ApiOperation(value = "获取店铺详细信息", notes = "TODO：取得某个店铺的详细信息")
    @ApiImplicitParam(name = "id", value = "店铺ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        //1根据店铺id取得店铺详情
        ResShop resShop = resShopService.selectResShopById(id);
        if (resShop == null){
            throw new BizException(404,"获取店铺失败");
        }
        //2店铺对象resShop存到redis中
        String str= JSON.toJSONString(resShop);
        redisCache.setCacheObject(String.valueOf(id), str, 1, TimeUnit.HOURS);
        //返回店铺对象
        return  AjaxResult.success(resShop);
    }

    /**
     * 新增店铺
     */
    @ApiOperation("新增店铺")
    @PostMapping
    @Log(title = "店铺管理",businessType = BusinessType.INSERT)
    public AjaxResult add(@Validated @RequestBody ResShop resShop) {
        return AjaxResult.success(resShopService.insertResShop(resShop));
    }

    /**
     * 修改店铺
     */
    @ApiOperation("修改店铺")
    @PutMapping
    @Log(title = "店铺管理",businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody ResShop resShop) {
        //1线上数据库查看店铺是否存在
        ResShop resShopDb = resShopService.selectResShopById(resShop.getId());
        //2判断店铺是否存在，不存在则业务异常
        if (resShopDb == null){
            throw new BizException(404,"店铺不存在");
        }
        //3店铺存在执行更新操作
        int num = resShopService.updateResShop(resShop);
        return AjaxResult.success(num);
    }

    /**
     * 删除店铺
     */
    @ApiOperation("删除店铺")
    @ApiImplicitParam(name = "id", value = "店铺ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    @Log(title = "店铺管理",businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long id) {
        // 根据店铺ID查询数据库中是否存在
        ResShop resShop = resShopService.selectResShopById(id);
        // 不存在的话，抛出异常BizException
        if(resShop == null) {
            throw new BizException(404, "该店铺id" + id + "不存在");
        }
        log.info("该店铺id" + id + "存在");
        return AjaxResult.success(resShopService.deleteResShopById(id));
    }
}

