package com.ruoyi.resturant.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


/**
 * 店铺对象 res_shop
 *
 * @author Daniel
 * @date 2023-09-27
 */
@ApiModel(value = "店铺实体", description = "店铺实体")
public class ResShop
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 店铺名称 */
    @NotBlank(message ="店铺名称不能为空")
    @Size(min = 3,max = 20,message = "店铺名称长度在3到20个字符之间")
    @ApiModelProperty("店铺名称")
    private String name;

    /** 店铺地址 */
    @ApiModelProperty("店铺地址")
    private String address;

    /** 联系电话 */
    @NotNull(message = "联系电话不能为空")
    @ApiModelProperty("联系电话")
    private String contact;

    /** 封面 */
    @ApiModelProperty("封面")
    private String cover;

    /** 店铺详情 */
    @ApiModelProperty("店铺详情")
    private String detail;

    /** 评分 */
    @ApiModelProperty("评分")
    private Long star;

    /** 店主id */
    @ApiModelProperty("店主id")
    private Long ownerId;

    /** 创建者 */
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

    public ResShop() {

    }
//    public ResShop(Long id, String name, String address, String contact)
//    {
//        this.id = id;
//        this.name = name;
//        this.address = address;
//        this.contact = contact;
//    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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

