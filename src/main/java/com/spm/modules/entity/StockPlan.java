package com.spm.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="spm_stock_plan")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "spm_stock_plan")
public class StockPlan implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @ApiModelProperty(value="")
    private Integer id;

    /**
     * 标题
     */
    @TableField(value = "title")
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 进货内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="进货内容")
    private String content;

    /**
     * 进货状态，1：进行中，2：已完成，3：未完成关闭
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value="进货状态，1：进行中，2：已完成，3：未完成关闭")
    private Integer status;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TITLE = "title";

    public static final String COL_CONTENT = "content";

    public static final String COL_STATUS = "status";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_DEL_FLAG = "del_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}