package com.cha.internet.bar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cha.internet.bar.entity.CustomerEntity;
import com.cha.internet.bar.entity.NetPlayRecordEntity;
import com.cha.internet.bar.entity.RechargeRecordEntity;
import com.cha.internet.bar.service.ICustomerService;
import com.cha.internet.bar.service.INetPlayRecordService;
import com.cha.internet.bar.service.IRechargeRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/recharge/record")
public class RechargeRecordController {
    //1块钱900s
    int one = 900;

    @Autowired
    private IRechargeRecordService rechargeRecordService;

    @Autowired
    private INetPlayRecordService netPlayRecordService;

    @Autowired
    private ICustomerService customerService;


    @ApiOperation(value = "充值金额-直接转换上网时间", httpMethod = "POST")
    @RequestMapping(value = "recharge/play", method = RequestMethod.POST)
    public void rechargePlay(String idCard, int money) {
        RechargeRecordEntity entity = new RechargeRecordEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setIdCard(idCard);
        entity.setChangeMoney(String.valueOf(money));
        entity.setType("ADD");
        entity.setDateCreate(new Date());
        entity.setDateUpdate(new Date());
        rechargeRecordService.save(entity);
        QueryWrapper<NetPlayRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(NetPlayRecordEntity.ID_CARD, idCard).eq(NetPlayRecordEntity.DATE_DELETE, 0);
        NetPlayRecordEntity old = netPlayRecordService.getOne(queryWrapper);
        if (old == null) {
            // 之前未开卡
            NetPlayRecordEntity netPlayRecordEntity = new NetPlayRecordEntity();
            netPlayRecordEntity.setId(UUID.randomUUID().toString());
            netPlayRecordEntity.setIdCard(idCard);
            int duration = money * one;
            netPlayRecordEntity.setDuration(String.valueOf(duration));
            Date now = new Date();
            netPlayRecordEntity.setStartTime(now);
            netPlayRecordEntity.setEndTime(new Date(System.currentTimeMillis() + duration * 1000));
            netPlayRecordEntity.setDateCreate(now);
            netPlayRecordEntity.setDateUpdate(now);
            netPlayRecordService.save(netPlayRecordEntity);
        } else {
            // 之前已经开卡
            Date now = new Date();
            int oldDuration = Integer.parseInt(old.getDuration());
            int duration = money * one + oldDuration;
            old.setDuration(String.valueOf(duration));
            old.setEndTime(new Date(System.currentTimeMillis() + duration * 1000));
            old.setDateUpdate(now);
            netPlayRecordService.updateById(old);
        }
        QueryWrapper<CustomerEntity> q = new QueryWrapper<>();
        q.eq(CustomerEntity.ID_CARD, idCard).eq(CustomerEntity.DATE_DELETE, 0);
        CustomerEntity customer = customerService.getOne(q);
        customer.setActivationState(1);
        customerService.updateById(customer);

    }


    @ApiOperation(value = "查看会员的充值记录", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<RechargeRecordEntity> login(String idCard) {

        QueryWrapper<RechargeRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RechargeRecordEntity.ID_CARD, idCard);
        List<RechargeRecordEntity> rechargeRecordEntities = rechargeRecordService.list(queryWrapper);
        rechargeRecordEntities.sort(Comparator.comparing(RechargeRecordEntity::getDateUpdate).reversed());

        return rechargeRecordEntities;
    }


    @ApiOperation(value = "仅仅充值金额", httpMethod = "POST")
    @RequestMapping(value = "recharge", method = RequestMethod.POST)
    public void recharge(String idCard, int money) {
        RechargeRecordEntity entity = new RechargeRecordEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setIdCard(idCard);
        entity.setChangeMoney(String.valueOf(money));
        entity.setType("ADD");
        entity.setDateCreate(new Date());
        entity.setDateUpdate(new Date());
        rechargeRecordService.save(entity);
        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CustomerEntity.ID_CARD, idCard);
        CustomerEntity customerEntity = customerService.getOne(queryWrapper);
        int nowMoney = Integer.parseInt(customerEntity.getMoney()) + money;
        customerEntity.setMoney(String.valueOf(nowMoney));
        customerService.save(customerEntity);
    }

    @ApiOperation(value = "上网", httpMethod = "POST")
    @RequestMapping(value = "play", method = RequestMethod.POST)
    public boolean play(String idCard) {
        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CustomerEntity.ID_CARD, idCard);
        CustomerEntity customerEntity = customerService.getOne(queryWrapper);
        int oldMoney = Integer.parseInt(customerEntity.getMoney());
        if (oldMoney < 6) {
            return false;
        }
        NetPlayRecordEntity netPlayRecordEntity = new NetPlayRecordEntity();
        netPlayRecordEntity.setId(UUID.randomUUID().toString());
        netPlayRecordEntity.setIdCard(idCard);
        int duration = oldMoney * one;
        netPlayRecordEntity.setDuration(String.valueOf(duration));
        Date now = new Date();
        netPlayRecordEntity.setStartTime(now);
        netPlayRecordEntity.setEndTime(new Date(System.currentTimeMillis() + duration * 1000));
        netPlayRecordEntity.setDateCreate(now);
        netPlayRecordEntity.setDateUpdate(now);
        netPlayRecordService.save(netPlayRecordEntity);

        QueryWrapper<CustomerEntity> q = new QueryWrapper<>();
        q.eq(CustomerEntity.ID_CARD, idCard).eq(CustomerEntity.DATE_DELETE, 0);
        CustomerEntity customer = customerService.getOne(q);
        customer.setActivationState(1);
        customer.setDateUpdate(new Date());
        customerService.updateById(customer);
        return false;

    }

    @ApiOperation(value = "下机", httpMethod = "POST")
    @RequestMapping(value = "offline", method = RequestMethod.POST)
    public void offline(String idCard) {

        QueryWrapper<NetPlayRecordEntity> q1 = new QueryWrapper<>();
        q1.eq(NetPlayRecordEntity.ID_CARD, idCard).eq(NetPlayRecordEntity.DATE_DELETE, 0);
        NetPlayRecordEntity old = netPlayRecordService.getOne(q1);

        int money = Integer.parseInt(String.valueOf(old.getEndTime().getTime() - System.currentTimeMillis())) / 900000;


        QueryWrapper<CustomerEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CustomerEntity.ID_CARD, idCard);
        CustomerEntity customerEntity = customerService.getOne(queryWrapper);
        customerEntity.setActivationState(0);
        customerEntity.setDateUpdate(new Date());
        customerEntity.setMoney(String.valueOf(money));
        customerService.updateById(customerEntity);
        //软删上网时间
        NetPlayRecordEntity netPlayRecordEntity = new NetPlayRecordEntity();
        netPlayRecordEntity.setDateDelete(new Date().getTime());
        netPlayRecordEntity.setDateUpdate(new Date());
        QueryWrapper<NetPlayRecordEntity> q = new QueryWrapper<>();
        q.eq(NetPlayRecordEntity.ID_CARD, idCard);
        netPlayRecordService.update(netPlayRecordEntity, q);

    }


}
