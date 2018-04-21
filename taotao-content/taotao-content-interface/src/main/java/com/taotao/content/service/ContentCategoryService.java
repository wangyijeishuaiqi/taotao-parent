package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */
public interface ContentCategoryService {

    //获取内容分类列表
    List<EasyUITreeNode> getContentCategoryList(long parentid);

    //添加内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
    TaotaoResult addContextCategory(long parentid,String name);

    //修改内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
    TaotaoResult updateContentCategory(long id,String name);


    //删除内容分类，注意参数名称要与content-category.jsp页面指定的参数名称一致
    TaotaoResult deleteContentCategory(long id);



}
