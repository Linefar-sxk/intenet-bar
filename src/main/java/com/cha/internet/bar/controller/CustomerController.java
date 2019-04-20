package com.cha.internet.bar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cha.internet.bar.config.BeanConvertUtil;
import com.cha.internet.bar.controller.resp.CustomerResp;
import com.cha.internet.bar.entity.CustomerEntity;
import com.cha.internet.bar.entity.NetPlayRecordEntity;
import com.cha.internet.bar.service.ICustomerService;
import com.cha.internet.bar.service.INetPlayRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.spi.CurrencyNameProvider;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@Api(value = "customer", description = "客户")
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private INetPlayRecordService netPlayRecordService;


    @ApiOperation(value = "注册", httpMethod = "POST")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(String name,
                           String idCard,
                           String money,
                           String type,
                           String password) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(UUID.randomUUID().toString());
        customerEntity.setName(name);
        customerEntity.setIdCard(idCard);
        customerEntity.setMoney(money);
        //type  COMMON 不是会员 YEAR MONTH
        customerEntity.setType(type);
        // 当前是否上网用户 0 不是 1是
        customerEntity.setActivationState(0);
        customerEntity.setPassword(password);
        customerEntity.setDateCreate(new Date());
        customerEntity.setDateUpdate(new Date());
        customerService.save(customerEntity);
        return customerEntity.getId();
    }

    @ApiOperation(value = "修改", httpMethod = "POST")
    @RequestMapping(value = "update/{customerId}", method = RequestMethod.POST)
    public String update(@PathVariable String customerId,
                         String name,
                         String idCard,
                         String type,
                         String password) {

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerId);
        customerEntity.setName(name);
        if (!StringUtils.isEmpty(idCard)) {
            customerEntity.setType(idCard);
        }
        if (!StringUtils.isEmpty(name)) {
            customerEntity.setType(name);
        }
        if (!StringUtils.isEmpty(type)) {
            customerEntity.setType(type);
        }
        if (!StringUtils.isEmpty(password)) {
            customerEntity.setPassword(password);
        }
        customerEntity.setDateUpdate(new Date());
        customerService.updateById(customerEntity);
        return customerEntity.getId();
    }

    @ApiOperation(value = "修改密码", httpMethod = "POST")
    @RequestMapping(value = "update/password/{idCard}", method = RequestMethod.POST)
    public boolean register(String idCard,
                            String password) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setPassword(password);
        customerEntity.setDateUpdate(new Date());

        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CustomerEntity.ID_CARD, idCard);
        customerService.update(customerEntity, queryWrapper);
        return true;
    }


    @ApiOperation(value = "删除用户", httpMethod = "POST")
    @RequestMapping(value = "delete/{customerId}", method = RequestMethod.POST)
    public boolean delete(@PathVariable String customerId) {
        customerService.removeById(customerId);
        return true;
    }

    @ApiOperation(value = "查询所有用户根据类型", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<CustomerResp> list(String type,
                                   Integer activationState) {
        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(type)) {
            queryWrapper.eq(CustomerEntity.TYPE, type);
        }
        if (activationState != null) {
            queryWrapper.eq(CustomerEntity.ACTIVATION_STATE, activationState);
        }
        List<CustomerEntity> customerEntities = customerService.list(queryWrapper);
        List<CustomerResp> resp = BeanConvertUtil.listConvert(customerEntities, CustomerResp.class);
        QueryWrapper<NetPlayRecordEntity> q2 = new QueryWrapper<>();
        List<String> idCrads = customerEntities.stream().map(CustomerEntity::getIdCard).collect(Collectors.toList());
//        q2.in(NetPlayRecordEntity.ID_CARD, idCrads).eq(NetPlayRecordEntity.DATE_DELETE, 0);
        if (idCrads.size() > 0){
            q2.in(NetPlayRecordEntity.ID_CARD, idCrads);
        }
        q2.eq(NetPlayRecordEntity.DATE_DELETE,null);
        List<NetPlayRecordEntity> nets = netPlayRecordService.list(q2);
        Map<String, NetPlayRecordEntity> netMap = nets.stream().collect(Collectors.toMap(NetPlayRecordEntity::getIdCard, Function.identity(), (k1, k2) -> k1));
        for(CustomerResp temp:resp){
            NetPlayRecordEntity entity=netMap.get(temp.getIdCard());
            if(entity !=null){
                temp.setStartTime(entity.getStartTime());
            }

        }

        return resp;
    }

    @ApiOperation(value = "根据id查询用户信息",httpMethod = "GET")
    @RequestMapping(value = "getByCustomerId",method = RequestMethod.GET)
    public CustomerEntity getByCustomerId(String customerId){
        CustomerEntity customerEntity = customerService.getById(customerId);
        return customerEntity;
    }

}
