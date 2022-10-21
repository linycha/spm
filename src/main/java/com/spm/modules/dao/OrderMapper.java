package com.spm.modules.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.modules.dto.OrderCountDTO;
import com.spm.modules.dto.OrderReturnDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Order;

public interface OrderMapper extends BaseMapper<Order> {
    IPage<OrderReturnDTO> selectPage(IPage<Object> page);

    /**
     * 查询年月日订单统计
     * @param page
     * @return
     */
    IPage<OrderCountDTO> selectOrderCount(IPage<Object> page,QueryDTO dto);

}