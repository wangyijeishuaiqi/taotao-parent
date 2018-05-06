package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * Created by hao002ku on 2018-04-24.
 */
public interface UserService {
    //检查用户数据是否合法，data便是参数值i，type指类型
    TaotaoResult checkUserData(String data, int type);
    //注册
    TaotaoResult register(TbUser tbUser);
    //登录
    TaotaoResult login(String username,String password);
    //通过token获取用户信息
    TaotaoResult getUserByToken(String token);
    //安全退出
    TaotaoResult logout(String token);
}
