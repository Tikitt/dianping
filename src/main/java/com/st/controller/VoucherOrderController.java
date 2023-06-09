package com.st.controller;


import com.st.dto.Result;
import com.st.entity.VoucherOrder;
import com.st.service.IVoucherOrderService;
import com.st.service.impl.VoucherOrderServiceImpl;
import com.st.service.impl.VoucherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/voucher-order")
public class VoucherOrderController {
    @Autowired
    IVoucherOrderService voucherService;
    @PostMapping("seckill/{id}")
    public Result seckillVoucher(@PathVariable("id") Long voucherId) {
        return voucherService.seckillVoucher(voucherId);
//        return Result.fail("功能未完成");
    }
}
