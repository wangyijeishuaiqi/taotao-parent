package com.taotao.service;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface ItemService {
    /**
     * 根据商品id获取商品信息
     * @param itemId
     * @return
     */
    TbItem getItemById(long itemId);

    /**
     * 分页查询商品信息
     * @param page
     * @param rows
     * @return
     */
    EasyUIDataGridResult getItemList(int page, int rows);
    TbItemDesc getItemDescById(long itemId);
    /**
     * 添加商品
     */
    TaotaoResult createItem (TbItem tbItem,String desc) throws Exception;

    /**
     * 删除商品
     */
    TaotaoResult deleteItem(String ids);

    /**
     * 编辑商品
     */
    TaotaoResult updateContent(TbItem Item);

    /**
     * 获取单个内容信息
     */
    TaotaoResult getContent(long id);

    /**
     * 根据内容分类id来获取内容列表
     * @param cid
     * @return
     */
    List<TbItem> getContentListByCid(long cid);

    /**
     * 上架
     */
    TaotaoResult reshelfItem(@RequestParam("ids") long itemId, TbItem item);

    /**
     * 下架
     */
    TaotaoResult instockItem(@RequestParam("ids") long itemId,TbItem item);

    //新增商品规格
    public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws Exception;

}
