package com.ruoyi.resturant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {
    @ApiModelProperty("编号")
    private Long id;
    @NotNull(message = "学号不能为空")
    @ApiModelProperty("学号")
    private String sno;
    @NotBlank(message = "姓名不能为空")
    @Size(min = 1,max = 20, message = "姓名长度必须在1-20之间")
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("性别")
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sno='" + sno + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
