package com.taotao.controller;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable long itemId) {
        return itemService.getItemById(itemId);
    }


    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(int page, int rows){

        return itemService.getItemList(page,rows);
    }


    @RequestMapping(value="/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addItem(TbItem item,String desc){
        try {
            TaotaoResult result=itemService.createItem(item,desc);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500,"添加商品失败！");
        }
    }
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public TaotaoResult deleteItem(String ids){
        TaotaoResult taotaoResult = itemService.deleteItem(ids);
        return taotaoResult;
    }

    @RequestMapping("/rest/item/update")
    @ResponseBody
    public TaotaoResult updateContent(TbItem Item){
        TaotaoResult result=itemService.updateContent(Item);
        return result;
    }

    @RequestMapping("")
    @ResponseBody
    public TaotaoResult getContent(Long id){
        TaotaoResult result=itemService.getContent(id);
        return result;
    }

    //上架商品
    @RequestMapping(value="/rest/item/reshelf",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult reshelfItem(@RequestParam("ids") long itemId, TbItem item){
        TaotaoResult result=itemService.reshelfItem(itemId, item);
        return result;
    }
    //下架商品
    @RequestMapping(value="/rest/item/instock",method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult instockItem(@RequestParam("ids") long itemId, TbItem item){
        TaotaoResult result=itemService.instockItem(itemId,item);
        return result;
    }

    @RequestMapping(value = "/item/param/save/", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult addItem(TbItem item, String desc, String itemParams) {
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setParamData(itemParams);

        TaotaoResult result = null;
        try {
            result = itemService.addItem(item, itemDesc, itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
