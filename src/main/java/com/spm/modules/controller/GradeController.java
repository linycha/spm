package com.spm.modules.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.common.Res;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.StockPlan;
import com.spm.modules.service.GradeService;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* (spm_grade)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("grade")
public class GradeController {
    /**
     * 服务对象
     */
    @Resource
    private GradeService gradeService;

    /**
     * 查询数据
     *
     * @return 分页数据
     */
    @GetMapping("list")
    public Res<IPage<Grade>> selectList(QueryDTO dto) {
        IPage<Grade> list = gradeService.selectPage(dto);
        return Res.success(list);
    }

    /**
     * 根据id值在表里是否存在判断是新增还是修改操作
     * @param entity
     * @return
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Grade entity){
        //字母A+手机号等于会员卡号
        if(entity.getId() == null){
            int count = gradeService.selectByMobile(entity.getMobile());
            if(count > 0) {
                return Res.errorMsg("手机号已重复");
            }
            entity.setCardNumber("A"+entity.getMobile());
        }
        boolean result = gradeService.saveOrUpdate(entity);
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
        List<Grade> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e->
                list.add(Grade.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = gradeService.updateBatchById(list);
        if(result){
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }

}
