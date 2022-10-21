package com.spm.modules.controller;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.spm.common.Const;
import com.spm.common.Res;
import com.spm.modules.dto.OrderCountDTO;
import com.spm.modules.dto.OrderReturnDTO;
import com.spm.modules.dto.OrderSaveDTO;
import com.spm.modules.dto.QueryDTO;
import com.spm.modules.entity.Grade;
import com.spm.modules.entity.Order;
import com.spm.modules.entity.Product;
import com.spm.modules.entity.StockPlan;
import com.spm.modules.service.GradeService;
import com.spm.modules.service.OrderService;
import com.spm.modules.service.ProductService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* (spm_order)表控制层
*
* @author xxxxx
*/
@RestController
@RequestMapping("order")
public class OrderController {
    /**
     * 服务对象
     */
    @Resource
    private OrderService orderService;
    @Resource
    private GradeService gradeService;
    @Resource
    private ProductService productService;

    /**
     * 查询交易清单数据
     */
    @GetMapping("list")
    public Res<IPage<OrderReturnDTO>> selectList(QueryDTO dto) {
        IPage<OrderReturnDTO> list = orderService.selectPage(dto);
        return Res.success(list);
    }

    /**
     * 查询年月日订单统计
     */
    @GetMapping("count")
    public Res<IPage<OrderCountDTO>> selectCount(QueryDTO dto) {
        IPage<OrderCountDTO> list = orderService.selectOrderCount(dto);
        return Res.success(list);
    }

    /**
     * 保存商品订单
     * @param dto
     * @return
     */
    @PostMapping("save")
    public Res<String> saveOrder(@RequestBody OrderSaveDTO dto){
        if(CollectionUtils.isEmpty(dto.getProductList())){
            return Res.errorMsg("商品列表不能为空");
        }
        Grade grade = null;
        if(StringUtils.isNotBlank(dto.getCardNumber())){
            grade = gradeService.selectOne(dto.getCardNumber());
            if(grade == null){
                return Res.errorMsg("无该会员卡号账号！");
            }
        }
        BigDecimal payPriceSum = BigDecimal.ZERO;
        //设置剩余库存productList
        List<Product> productList = new ArrayList<>();
        Long orderNo = Const.generateOrderNo();
        for(Order item : dto.getProductList()){
            productList.add(Product.builder()
                    .id(item.getProductId())
                    .stock(item.getStock()-item.getNum()).build());
            item.setOrderNo(orderNo);
            //果然是会员设置会员卡号，不是会员使用原价
            if(grade != null){
                item.setGradeId(grade.getId());
            }else {
                //使用商品原价
                item.setPayPrice(item.getTotalPrice());
            }
            payPriceSum = payPriceSum.add(item.getPayPrice());
            item.setStock(null);
        }
        //保存订单
        boolean result = orderService.saveBatch(dto.getProductList());
        if(!result){
            return Res.errorMsg("结算失败");
        }
        //会员扣除积分
        //设置会员信息的累计积分值,累计支付金额
        if(grade != null){
            String str = payPriceSum.toString();
            int end = str.indexOf(".");
            int integrate = end == -1 ? Integer.parseInt(str) : Integer.parseInt(str.substring(0,end));
            grade.setIntegrate(grade.getIntegrate()+integrate);
            grade.setPayment(grade.getPayment().add(payPriceSum));
            gradeService.updateById(grade);
        }
        //扣库存
        productService.updateBatchById(productList);
        return Res.successMsg("结算成功");
    }

    @DeleteMapping("delete")
    public Res<String> delete(String ids){
        if(StringUtils.isBlank(ids)){
            return Res.errorMsg("id不能为空！");
        }
        List<Order> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e->
                list.add(Order.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = orderService.updateBatchById(list);
        if(result){
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }

}
