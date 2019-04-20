package com.cha.internet.bar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cha.internet.bar.controller.resp.CustomerResp;
import com.cha.internet.bar.entity.AdminEntity;
import com.cha.internet.bar.entity.NetPlayRecordEntity;
import com.cha.internet.bar.service.INetPlayRecordService;
import com.cha.internet.bar.service.impl.NetPlayRecordServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@RestController
@RequestMapping("/net/play")
public class NetPlayRecordController {

    @Autowired
    private INetPlayRecordService netPlayRecordService;

    @ApiOperation(value = "查看会员的上网时间", httpMethod = "GET")
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<NetPlayRecordEntity> login(String idCard) {

        QueryWrapper<NetPlayRecordEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(NetPlayRecordEntity.ID_CARD, idCard);
        List<NetPlayRecordEntity> netPlayRecordEntities = netPlayRecordService.list(queryWrapper);
        netPlayRecordEntities.sort(Comparator.comparing(NetPlayRecordEntity::getDateUpdate).reversed());

        return netPlayRecordEntities;

    }

}
