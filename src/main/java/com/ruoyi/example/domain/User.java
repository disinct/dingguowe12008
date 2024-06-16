package com.ruoyi.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
@ApiModel(value = "User", description = "用户实体")

public class User {
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("用户ID")
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
