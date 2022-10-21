package com.spm.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
    * 商品表
    */
@ApiModel(value="商品表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "spm_product")
public class Product implements Serializable {
    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
     * 商品名称
     */
    @TableField(value = "`name`")
    @ApiModelProperty(value="商品名称")
    private String name;

    /**
     * 商品描述
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="商品描述")
    private String remark;

    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="价格")
    private BigDecimal price;

    /**
     * 会员价
     */
    @TableField(value = "grade_price")
    @ApiModelProperty(value="会员价")
    private BigDecimal gradePrice;

    /**
     * 生产日期
     */
    @TableField(value = "production_date")
    @ApiModelProperty(value="生产日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    /**
     * 保证期，天
     */
    @TableField(value = "expiration_date")
    @ApiModelProperty(value="保质期，天")
    private Integer expirationDate;

    /**
     * 停售时间
     */
    @TableField(value = "closed_date")
    @ApiModelProperty(value="停售时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date closedDate;

    /**
     * 库存数量
     */
    @TableField(value = "stock")
    @ApiModelProperty(value="库存数量")
    private Integer stock;

    /**
     * 商品状态.1-在售 2-下架 3:缺货
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="商品状态.1-在售 2-下架 3:缺货")
    private Integer status;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 0：未删除 1：已删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="0：未删除 1：已删除")
    private Boolean delFlag;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    /**
     * 是否过期
     */
    private Integer isOverdue;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_REMARK = "remark";

    public static final String COL_PRICE = "price";

    public static final String COL_GRADE_PRICE = "grade_price";

    public static final String COL_PRODUCTION_DATE = "production_date";

    public static final String COL_EXPIRATION_DATE = "expiration_date";

    public static final String COL_CLOSED_DATE = "closed_date";

    public static final String COL_STOCK = "stock";

    public static final String COL_STATUS = "status";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}