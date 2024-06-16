package com.ruoyi.resturant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(value = "菜品实体", description = "菜品实体")
public class ResDish {
    //编号
    @ApiModelProperty("编号")
    private Long id;

    //菜品名称
    @NotBlank(message ="菜品名称不能为空")
    @Size(min = 3,max = 20,message = "菜品名称长度在3到20个字符之间")
    @ApiModelProperty("菜品名称")
    private String name;

    //菜品图片
    @ApiModelProperty("菜品图片")
    private String pic;

    //菜品描述
    @ApiModelProperty("菜品描述")
    private String description;

    //菜品价格
    @ApiModelProperty("菜品价格")
    private int price;

    //菜品分类
    @ApiModelProperty("菜品分类")
    private String category;

    //菜品ID
    @ApiModelProperty("所属店铺id")
    private Long shopId;

    //创建者
    @ApiModelProperty("创建者")
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty("更新者")
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
