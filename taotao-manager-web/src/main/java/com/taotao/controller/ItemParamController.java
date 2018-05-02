package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2018-04-27.
 */
@Controller
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 得到商品规格模板
     */
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(@RequestParam(defaultValue="1")Integer page, @RequestParam(defaultValue="30")Integer rows){
        return itemParamService.getItemParamList(page, rows);
    }
    /**
     * 删除商品规格
     */
    @RequestMapping("/item/param/delete")
    @ResponseBody
    public TaotaoResult deleteItemParam(String ids){
        TaotaoResult taotaoResult = itemParamService.deleteItemParam(ids);
        return taotaoResult;
    }

}
