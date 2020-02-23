package com.toesbieya.my.controller;

import com.toesbieya.my.model.entity.SysCustomer;
import com.toesbieya.my.model.vo.search.CustomerSearch;
import com.toesbieya.my.service.SysCustomerService;
import com.toesbieya.my.utils.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("system/customer")
public class SysCustomerController {
    @Resource
    private SysCustomerService customerService;

    @GetMapping("getLimitRegion")
    public Result getLimitRegion() {
        return Result.success(customerService.getLimitRegion());
    }

    @GetMapping("get")
    public Result get() {
        return Result.success(customerService.get());
    }

    @PostMapping("search")
    public Result search(@RequestBody CustomerSearch vo) {
        return Result.success(customerService.search(vo));
    }

    @PostMapping("add")
    public Result add(@RequestBody SysCustomer customer) {
        String errMsg = validateCreateParam(customer);
        if (errMsg != null) return Result.fail("创建失败，" + errMsg);

        customer.setCtime(System.currentTimeMillis());
        return customerService.add(customer);
    }

    @PostMapping("update")
    public Result update(@RequestBody SysCustomer customer) {
        String errMsg = validateUpdateParam(customer);
        if (errMsg != null) return Result.fail("修改失败，" + errMsg);

        return customerService.update(customer);
    }

    @PostMapping("del")
    public Result del(@RequestBody SysCustomer customer) {
        if (customer.getId() == null) return Result.fail("删除失败，参数错误");
        return customerService.del(customer);
    }

    private String validateCreateParam(SysCustomer customer) {
        if (StringUtils.isEmpty(customer.getName())) return "客户名称不能为空";
        if (StringUtils.isEmpty(customer.getRegion())) return "客户行政区域不能为空";
        if (StringUtils.isEmpty(customer.getAddress())) return "客户地址不能为空";
        if (StringUtils.isEmpty(customer.getLinkman())) return "客户联系人不能为空";
        if (StringUtils.isEmpty(customer.getLinkphone())) return "客户联系电话不能为空";
        if (customer.getStatus() == null) return "客户状态不能为空";
        return null;
    }

    private String validateUpdateParam(SysCustomer customer) {
        if (customer.getId() == null) return "参数错误";
        return validateCreateParam(customer);
    }
}
