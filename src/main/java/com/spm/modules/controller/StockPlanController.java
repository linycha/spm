package com.spm.modules.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.common.Res;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.StockPlan;
import com.spm.modules.service.StockPlanService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* (spm_stock_plan)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("stockPlan")
public class StockPlanController {
/**
* 服务对象
*/
@Resource
private StockPlanService stockPlanService;

    /**
     * 查询数据
     *
     * @return 分页数据
     */
    @GetMapping("list")
    public Res<IPage<StockPlan>> selectList(QueryDTO dto) {
        IPage<StockPlan> list = stockPlanService.selectPage(dto);
        return Res.success(list);
    }

    /**
     * 根据id值在表里是否存在判断是新增还是修改操作
     * @param entity
     * @return
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody StockPlan entity){
        entity.setCreateTime(null);
        entity.setUpdateTime(null);
        boolean result = stockPlanService.saveOrUpdate(entity);
        if(result){
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

    @DeleteMapping("delete")
    public Res<String> delete(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("id不能为空！");
        }
        List<StockPlan> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e->
                list.add(StockPlan.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = stockPlanService.updateBatchById(list);
        if(result){
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }

}
