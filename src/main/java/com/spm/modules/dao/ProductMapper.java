package com.spm.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Order;
import com.spm.modules.entity.Product;

public interface ProductMapper extends BaseMapper<Product> {
    IPage<Product> selectPage(IPage<Object> page, QueryDTO dto);
}