package com.spm.modules.dto;

import lombok.Data;

/**
 * 查询dto
 * @author linyuc
 * @Description TODO
 * @date 2022/1/13 1:53
 */
@Data
public class QueryDTO extends BaseDTO {

    /**
     * 原因
     */
    private String reason;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态
     */
    private String status;
    /**
     * 名称、用户名
     */
    private String name;
    /**
     * 是否过期
     */
    private Integer isOverdue;
    /**
     * 价格
     */
    private String price;
    /**
     * 标题
     */
    private String title;
    /**
     * 会员卡号
     */
    private String cardNumber;
    /**
     * 统计日期纬度（年月日）（'%Y-%m-%d'）
     */
    private String date;

}
