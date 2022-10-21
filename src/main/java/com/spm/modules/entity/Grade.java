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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@ApiModel(value="spm_grade")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "spm_grade")
public class Grade implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 卡号
     */
    @TableField(value = "card_number")
    @ApiModelProperty(value="卡号")
    private String cardNumber;

    @TableField(value = "`name`")
    @ApiModelProperty(value="")
    private String name;

    @TableField(value = "mobile")
    @ApiModelProperty(value="")
    private String mobile;

    /**
     * 生日
     */
    @TableField(value = "birthday")
    @ApiModelProperty(value="生日")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;

    /**
     * 有效截止时间
     */
    @TableField(value = "deadline")
    @ApiModelProperty(value="有效截止时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date deadline;

    /**
     * 支付总额
     */
    @TableField(value = "payment")
    @ApiModelProperty(value="支付总额")
    private BigDecimal payment;

    /**
     * 积分
     */
    @TableField(value = "integrate")
    @ApiModelProperty(value="积分")
    private Integer integrate;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_CARD_NUMBER = "card_number";

    public static final String COL_NAME = "name";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_BIRTHDAY = "birthday";

    public static final String COL_DEADLINE = "deadline";

    public static final String COL_PAYMENT = "payment";

    public static final String COL_INTEGRATE = "integrate";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}