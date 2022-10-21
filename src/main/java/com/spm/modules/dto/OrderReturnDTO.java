package com.spm.modules.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/19 23:52
 */
@Data
public class OrderReturnDTO implements Serializable {
    private Long orderNo;
    private String productStr;
    private String totalPrice;
    private String payPrice;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createTime;
}
