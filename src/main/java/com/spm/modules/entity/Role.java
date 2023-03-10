package com.spm.modules.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linyuc
 * @date 2019/12/27 11:28
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 2550610506071566713L;
    private String id;
    private String name;
    private List<Permission> permissions = new ArrayList<>();
}
