package com.pinyougou.cart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.order.service.OrderService;
import com.pinyougou.pay.service.WeixinPayService;
import com.pinyougou.pojo.TbPayLog;
import entity.Result;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.IdWorker;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 37269 on 2018/8/14.
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Reference(timeout = 1000*60*6)
    private WeixinPayService weixinPayService;

    @Reference
    private OrderService orderService;

    @RequestMapping("/createNative")
    public Map createNative(){
        String userId= SecurityContextHolder.getContext().getAuthentication().getName();
        //从缓存中提取payLog，目的是取出订单号和金额
        TbPayLog payLog = orderService.searchPayLogFromRedis(userId);
        if(payLog!=null){
            return weixinPayService.createNative(payLog.getOutTradeNo(),payLog.getTotalFee()+"");
        }else{
            return new HashMap();
        }
    }

    /**
     * 查询支付状态
     * @param out_trade_no
     * @return
     */
    @RequestMapping("/queryPayStatus")
    public Result queryPayStatus(String out_trade_no){

        Map map = weixinPayService.queryPayStatusWhile(out_trade_no);
        if(map==null){
            return new Result(false,"执行出错");
        }else{
            if("SUCCESS".equals( map.get("trade_state"))){
                //更改订单状态
                orderService.updateOrderStatus(out_trade_no,  (String)map.get("transaction_id"));
                return new Result(true,"支付成功");
            }else{
                return new Result(false,"超时");
            }
        }
    }

}
