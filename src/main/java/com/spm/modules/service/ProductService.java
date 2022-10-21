package com.spm.modules.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spm.common.utils.UserUtils;
import com.spm.modules.dto.QueryDTO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spm.modules.dao.ProductMapper;
import com.spm.modules.entity.Product;
@Service
public class ProductService extends ServiceImpl<ProductMapper, Product> {

    public IPage<Product> selectPage(QueryDTO dto){
        IPage<Object> page = new Page<>(dto.getCurrent(),dto.getSize());
        IPage<Product> list = this.baseMapper.selectPage(page,dto);
        //判断设置是否过期
        Date nowDate = DateUtil.parse(DateUtil.now(),"yyyy-MM-dd");
        list.getRecords().forEach(e->{
            int result = DateUtil.compare(nowDate, e.getClosedDate());
            e.setIsOverdue(result == 1 ? 1 : 0);
        });
        return list;
    }
}
