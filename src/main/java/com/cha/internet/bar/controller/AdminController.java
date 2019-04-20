package com.cha.internet.bar.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cha.internet.bar.entity.AdminEntity;
import com.cha.internet.bar.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author George
 * @since 2019-04-19
 */
@Api(value = "admin", description = "管理")
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "管理员登录", httpMethod = "POST")
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public boolean login(String mobile, String password) {
        QueryWrapper<AdminEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AdminEntity.MOBILE, mobile);
        AdminEntity entity = adminService.getOne(queryWrapper);
        if (entity == null) {
            return false;
        }
        return password.equals(entity.getPassword());
    }

    @ApiOperation(value = "登陆成功跳转会员列表页",httpMethod = "GET")
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "list";
    }


}
