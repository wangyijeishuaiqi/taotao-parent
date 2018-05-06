package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户处理Controller
 * @author xxxxxxxx
 *
 */
@Controller
public class Usercontroller {

    @Autowired UserService userService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public TaotaoResult checkUserData(@PathVariable String param, @PathVariable Integer type){
        TaotaoResult result = userService.checkUserData(param, type);
        return result;
    }
}
