package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by Administrator on 2018/4/27.
 */
public interface UserService {

//检查用户是否合法，data便是参数值，type是指类型
    TaotaoResult checkUserData(String data, int type);
}
