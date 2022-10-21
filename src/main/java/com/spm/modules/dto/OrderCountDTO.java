package com.spm.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/27 23:06
 */
@Data
public class OrderCountDTO implements Serializable {
    /**
     * 商品名
     */
    private String name;
    /**
     * 统计纬度：年月日
     */
    private String date;
    /**
     * 总销量
     */
    private Integer numSum;
    /**
     * 总支付金额
     */
    private String payPriceSum;
}
