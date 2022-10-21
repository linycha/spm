package com.spm.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.common.utils.UserUtils;
import com.spm.modules.dto.QueryDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.modules.dao.StockPlanMapper;
import com.spm.modules.entity.StockPlan;
@Service
public class StockPlanService extends ServiceImpl<StockPlanMapper, StockPlan> {
    public IPage<StockPlan> selectPage(QueryDTO dto){
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        return this.baseMapper.selectPage(page,dto);
    }
}
