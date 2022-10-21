package com.spm.modules.service;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.common.Res;
import com.spm.common.utils.UserUtils;
import com.spm.modules.dao.GradeMapper;
import com.spm.modules.dto.OrderCountDTO;
import com.spm.modules.dto.OrderReturnDTO;
import com.spm.modules.dto.OrderSaveDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Grade;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.modules.entity.Order;
import com.spm.modules.dao.OrderMapper;
@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
    @Autowired
    private GradeMapper gradeMapper;

    public IPage<OrderReturnDTO> selectPage(QueryDTO dto){
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        return this.baseMapper.selectPage(page);
    }
    /**
     * 查询年月日订单统计
     * @param dto
     * @return
     */
    public IPage<OrderCountDTO> selectOrderCount(QueryDTO dto){
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        return this.baseMapper.selectOrderCount(page, dto);
    }
}
