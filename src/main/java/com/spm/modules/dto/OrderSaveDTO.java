package com.spm.modules.dto;

import com.spm.modules.entity.Order;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/19 21:50
 */
@Data
public class OrderSaveDTO implements Serializable {
    /**
     * 会员卡号
     */
    private String cardNumber;
    /**
     * 商品列表
     */
    private List<Order> productList;
}
