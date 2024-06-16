package com.ruoyi.web.controller.example;


import com.ruoyi.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags="学生管理系统")
@Anonymous
@RestController
@RequestMapping("/example")
public class FirstController {
    @Value("${ruoyi.name}")
    private  String ruoyiName;
    @Value("${ruoyi.version}")
    private  String ruoyiVersion;
    @ApiOperation("Hello例子")
    @GetMapping("/first")
    public String Hello () {
        return "若依名字：" + ruoyiName
                + " 若依版本: " + ruoyiVersion;
    }
}
