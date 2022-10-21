package com.spm.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="spm_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "spm_order")
public class Order implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value="订单号")
    private Long orderNo;

    /**
     * 商品id
     */
    @TableField(value = "product_id")
    @ApiModelProperty(value="商品id")
    private Integer productId;

    /**
     * 会员id
     */
    @TableField(value = "grade_id")
    @ApiModelProperty(value="会员id")
    private Integer gradeId;

    /**
     * 商品购买数量
     */
    @TableField(value = "num")
    @ApiModelProperty(value="商品购买数量")
    private Integer num;

    /**
     * 总价
     */
    @TableField(value = "total_price")
    @ApiModelProperty(value="总价")
    private BigDecimal totalPrice;

    /**
     * 支付金额（优惠后）
     */
    @TableField(value = "pay_price")
    @ApiModelProperty(value="支付金额（优惠后）")
    private BigDecimal payPrice;

    @TableField(value = "remarks")
    @ApiModelProperty(value="")
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

    private Integer stock;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_PRODUCT_ID = "product_id";

    public static final String COL_GRADE_ID = "grade_id";

    public static final String COL_NUM = "num";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_PAY_PRICE = "pay_price";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}