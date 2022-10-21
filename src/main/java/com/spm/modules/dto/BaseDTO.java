package com.spm.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/13 1:52
 */
@Data
public class BaseDTO implements Serializable {
    /**
     * 当前页
     */
    private int current;
    /**
     * 当前页大小
     */
    private int size;
}
