package com.spm.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Product;
import com.spm.modules.entity.StockPlan;

public interface StockPlanMapper extends BaseMapper<StockPlan> {
    IPage<StockPlan> selectPage(IPage<Object> page, QueryDTO dto);

}