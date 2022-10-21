package com.spm.modules.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.modules.dto.QueryDTO;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.modules.entity.Grade;
import com.spm.modules.dao.GradeMapper;
@Service
public class GradeService extends ServiceImpl<GradeMapper, Grade> {
    public IPage<Grade> selectPage(QueryDTO dto){
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        return this.baseMapper.selectPage(page,dto);
    }
    public Grade selectOne(String cardNumber){
        return this.baseMapper.selectOne(cardNumber);
    }
    /**
     * 查找手机号是否存在
     * @param mobile
     * @return
     */
    public int selectByMobile(String mobile){
        return this.baseMapper.selectByMobile(mobile);
    }
}
