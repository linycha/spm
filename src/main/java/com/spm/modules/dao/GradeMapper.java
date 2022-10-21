package com.spm.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.Order;

public interface GradeMapper extends BaseMapper<Grade> {
    IPage<Grade> selectPage(IPage<Object> page, QueryDTO dto);
    Grade selectOne(String cardNumber);

    /**
     * 查找手机号是否存在
     * @param mobile
     * @return
     */
    int selectByMobile(String mobile);
}