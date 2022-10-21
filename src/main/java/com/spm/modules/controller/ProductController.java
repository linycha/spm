package com.spm.modules.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.common.Res;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Product;
import com.spm.modules.entity.StockPlan;
import com.spm.modules.service.ProductService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* (spm_product)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 查询数据
     *
     * @return 分页数据
     */
    @GetMapping("list")
    public Res<IPage<Product>> selectList(QueryDTO dto) {
        IPage<Product> list = productService.selectPage(dto);
        return Res.success(list);
    }

    /**
     * 根据id值在表里是否存在判断是新增还是修改操作
     * @param entity
     * @return
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Product  entity){
        entity.setIsOverdue(null);
        //设置过期时间
        entity.setClosedDate(DateUtil.offsetDay(entity.getProductionDate(),entity.getExpirationDate()));
        //果然库存为0设置状态为缺货2,否则为在售1
        entity.setStatus(entity.getStock() <= 0 ? 2 : 1);
        boolean result;
        if(entity.getId() != null){
            result = productService.updateById(entity);
        }else {
            result = productService.save(entity);
        }
        if(result){
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    /**
     * 批量删除商品
     * @param ids 逗号拼接id
     * @return
     */
    @DeleteMapping("delete")
    public Res<String> delete(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("id不能为空！");
        }
        List<Product> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e->
                list.add(Product.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = productService.updateBatchById(list);
        if(result){
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }

}
