package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by ASUS on 2018-04-27.
 */
public interface ItemParamService {
    //得到商品规格模板列表
    EasyUIDataGridResult getItemParamList(int page, int rows);
    //删除商品规格
    TaotaoResult deleteItemParam(String ids);

}
