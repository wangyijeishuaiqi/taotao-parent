package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18.
 */
@Service("itemServiceImpl")
public class ItemServiceimpl implements ItemService {

    @Autowired TbItemMapper itemMapper;
    @Autowired TbItemDescMapper tbItemDescMapper;
    @Autowired private TbItemParamItemMapper itemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId)
    {
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        return item;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        //执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> list = itemMapper.selectByExample(example);
        //取查询结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(list);
        result.setTotal(pageInfo.getTotal());
        //返回结果
        return result;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        TbItemDesc tbItemDesc=tbItemDescMapper.selectByPrimaryKey(itemId);
        return tbItemDesc;
    }
    @Override
    public TaotaoResult createItem(TbItem tbItem, String desc) throws Exception {
        //生成商品ID
        long itemId = IDUtils.genItemId();
        //补全item的属性
        tbItem.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        tbItem.setStatus(((byte) 1));
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        itemMapper.insert(tbItem);
        //添加商品描述
        insertItemDesc(itemId, desc);
        return TaotaoResult.ok();
    }

    //删除商品
    @Override
    public TaotaoResult deleteItem(String ids) {
        String[] idList = ids.split(",");
        for(String id : idList){
            //删除内容
            itemMapper.deleteByPrimaryKey(Long.valueOf(id));
        }
        //返回结果
        return TaotaoResult.ok();
    }

    //编辑商品
    @Override
    public TaotaoResult updateContent(TbItem Item) {
        Item.setUpdated(new Date());
        itemMapper.updateByPrimaryKey(Item);
        return  TaotaoResult.ok();
    }

    @Override
    public TaotaoResult getContent(long id) {
        TbItem Item =itemMapper.selectByPrimaryKey(id);
        return TaotaoResult.ok(Item);
    }

    @Override
    public List<TbItem> getContentListByCid(long cid) {
        TbItemExample itemExample=new TbItemExample();
        TbItemExample.Criteria criteria=itemExample.createCriteria();
        criteria.andCidEqualTo(cid);
        List<TbItem> list=itemMapper.selectByExample(itemExample);
        return list;
    }

    //上架
    @Override
    public TaotaoResult reshelfItem(long itemId, TbItem item) {
        item=itemMapper.selectByPrimaryKey(itemId);
        item.setStatus((byte) 1);
        item.setCreated(item.getCreated());
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        return TaotaoResult.ok();
    }

    //下架
    @Override
    public TaotaoResult instockItem(@RequestParam("ids") long itemId, TbItem item) {
        item=itemMapper.selectByPrimaryKey(itemId);
        item.setStatus((byte) 2);
        item.setCreated(item.getCreated());
        item.setUpdated(new Date());
        itemMapper.updateByPrimaryKeySelective(item);
        return TaotaoResult.ok();
    }

    //添加商品描述
    private void insertItemDesc(@RequestParam("ids") long itemId,String desc){
        //创建一个商品描述表对应的pojo
        TbItemDesc itemDesc = new TbItemDesc();
        //补全pojo的属性
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());
        //向商品描述表插入数据
        tbItemDescMapper.insert(itemDesc);
    }
    @Override
    public TaotaoResult addItem(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) throws Exception {
        try {
            //生成商品id，使用时间+随机数策略生成
            Long itemId = IDUtils.genItemId();

            //补全商品信息
            item.setId(itemId);
            item.setStatus((byte) 1);
            item.setCreated(new Date());
            item.setUpdated(new Date());
            //把数据插入到商品表
            itemMapper.insert(item);

            //补全商品描述信息
            itemDesc.setItemId(itemId);
            itemDesc.setCreated(new Date());
            itemDesc.setUpdated(new Date());
            //把数据插入到商品描述表
            tbItemDescMapper.insert(itemDesc);

            //补全商品规格参数
            itemParamItem.setItemId(itemId);
            itemParamItem.setCreated(new Date());
            itemParamItem.setUpdated(new Date());
            //插入商品规格到商品规格表
            itemParamItemMapper.insert(itemParamItem);
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
        }

        return TaotaoResult.ok();
    }
}
