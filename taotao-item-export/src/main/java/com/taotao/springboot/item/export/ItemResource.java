package com.taotao.springboot.item.export;

import com.taotao.springboot.item.domain.pojo.TbItem;
import com.taotao.springboot.item.domain.pojo.TbItemDesc;
import com.taotao.springboot.item.domain.result.EasyUIDataGridResult;
import com.taotao.springboot.item.domain.result.TaotaoResult;

/**
 * <p>Title: ItemResource</p>
 * <p>Description: </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:25</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface ItemResource {

    /**
     * 根据商品ID查询商品
     */
    TbItem getItemById(long itemId);

    /**
     * 查询商品列表
     */
    EasyUIDataGridResult getItemList(int page, int rows);

    /**
     * 商品添加
     */
    TaotaoResult addItem(TbItem item, String desc);

    /**
     * 根据商品ID获取详情
     */
    TbItemDesc getItemDescById(long itemId);

}
