package com.spm.modules.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linyuc
 * @Description TODO
 * @date 2022/1/24 0:14
 */
@Data
public class UserDTO implements Serializable {
    private Integer userId;
    private Integer roleId;
}
