package com.ruoyi.web.controller.resturant;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BizException;
import com.ruoyi.resturant.domain.ResDish;
import com.ruoyi.resturant.service.IResDishService;
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
@RequestMapping("/api/resturant/dish")
@Api(tags="菜品管理")
public class ResDishController extends BaseController {
    @Autowired
    private IResDishService resDishService;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询菜品列表
     */
    @ApiOperation("查询菜品列表")
    @ApiImplicitParam(name = "name", value = "菜品名称",
            dataType = "string", dataTypeClass = String.class)
    @GetMapping("/list")
    public AjaxResult list(@RequestParam(value = "name", required = false) String dishName) {
        List<ResDish> list = resDishService.selectResDishByParam(dishName);
        return AjaxResult.success(list);
    }

    /**
     * 获取菜品详细信息
     */
    @ApiOperation(value = "获取菜品详细信息", notes = "TODO：取得某个菜品的详细信息")
    @ApiImplicitParam(name = "id", value = "菜品ID",
            required = true, dataType = "long", paramType = "path",
            dataTypeClass = Long.class)
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        //1根据菜品id取得菜品详情
        ResDish resDish = resDishService.selectResDishById(id);
        if (resDish == null){
            throw new BizException(404,"获取菜品失败");
        }
        //2菜品对象resDish存到redis中
        String str= JSON.toJSONString(resDish);
        redisCache.setCacheObject(String.valueOf(id), str, 1, TimeUnit.HOURS);
        //返回菜品对象
        return  AjaxResult.success(resDish);
    }

    /**
     * 新增菜品
     */
    @ApiOperation("新增菜品")
    @PostMapping
    @Log(title = "菜品管理",businessType = BusinessType.INSERT)
    public AjaxResult add(@Validated @RequestBody ResDish resDish) {
        return AjaxResult.success(resDishService.insertResDish(resDish));
    }

    /**
     * 修改菜品
     */
    @ApiOperation("修改菜品")
    @PutMapping
    @Log(title = "菜品管理",businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody ResDish resDish) {
        //1线上数据库查看菜品是否存在
        ResDish resDishDb = resDishService.selectResDishById(resDish.getId());
        //2判断菜品是否存在，不存在则业务异常
        if (resDishDb == null){
            throw new BizException(404,"菜品不存在");
        }
        //3菜品存在执行更新操作
        int num = resDishService.updateResDish(resDish);
        return AjaxResult.success(num);
    }

    /**
     * 删除菜品
     */
    @ApiOperation("删除菜品")
    @ApiImplicitParam(name = "id", value = "菜品ID", required = true, dataType = "long", paramType = "path", dataTypeClass = Long.class)
    @DeleteMapping("/{id}")
    @Log(title = "菜品管理",businessType = BusinessType.DELETE)
    public AjaxResult remove(@PathVariable Long id) {
        // 根据菜品ID查询数据库中是否存在
        ResDish resDish = resDishService.selectResDishById(id);
        // 不存在的话，抛出异常BizException
        if(resDish == null) {
            throw new BizException(404, "该菜品id" + id + "不存在");
        }
        log.info("该菜品id" + id + "存在");
        return AjaxResult.success(resDishService.deleteResDishById(id));
    }
}

