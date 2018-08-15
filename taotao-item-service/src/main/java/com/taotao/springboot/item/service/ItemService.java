package com.taotao.springboot.item.service;

import com.taotao.springboot.item.domain.pojo.TbItem;
import com.taotao.springboot.item.domain.pojo.TbItemDesc;
import com.taotao.springboot.item.domain.result.EasyUIDataGridResult;
import com.taotao.springboot.item.domain.result.TaotaoResult;

/**
 * <p>Title: ItemService</p>
 * <p>Description: 商品管理接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 16:56</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface ItemService {

    /**
     * 根据商品ID查询商品基本信息
     */
    TbItem getItemById(long itemId);

    /**
     * 根据商品ID获取商品详情
     */
    TbItemDesc getItemDescById(long itemId);

    /**
     * 查询商品列表
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 商品添加
     */
    TaotaoResult addItem(TbItem item, String desc);

}
