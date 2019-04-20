package com.cha.internet.bar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Auther: sun Xiangkai
 * @Date: 2019/4/20 16:17
 * @Description:
 */
@Controller
@RequestMapping("/dialog")
public class DialogViewController {

    /**
     * @Description : 新增会员页面
     * @Date 16:19 2019/4/20
     **/
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(){
        return "dialog/add";
    }

    /**
     * @Description : 会员信息修改页面
     * @Date 16:20 2019/4/20
     **/
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(){
        return "dialog/edit";
    }

    /**
     * @Description : 会员信息详情页面
     * @Date 16:21 2019/4/20
     **/
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(){
        return "dialog/detail";
    }

    /**
     * @Description : 登出
     * @Date 23:43 2019/4/20
     **/
    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public String loginOut(){
        return "index";
    }
}
