package com.spm.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author linyc
 * @date 2019/12/12 12:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"updateTime"})
@TableName(value = "sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = 7321352169002894594L;

    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private Integer personalId;

    private Integer companyId;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "sex")
    private String sex;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "status")
    private String status;

    @TableField(value = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    private Date updateTime;
    /**
     * 0：未删除 1：已删除
     */
    @TableField(value = "del_flag")
    @ApiModelProperty(value="0：未删除 1：已删除")
    private Boolean delFlag;

    private String[] roles;

    private String roleName;

    private List<Role> roleList = new ArrayList<>();

}