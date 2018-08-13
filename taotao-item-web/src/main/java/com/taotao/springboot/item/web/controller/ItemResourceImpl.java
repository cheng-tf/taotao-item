package com.taotao.springboot.item.web.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.pojo.TbItem;
import com.taotao.springboot.item.domain.pojo.TbItemDesc;
import com.taotao.springboot.item.domain.result.EasyUIDataGridResult;
import com.taotao.springboot.item.domain.result.TaotaoResult;
import com.taotao.springboot.item.export.ItemResource;
import com.taotao.springboot.item.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * <p>Title: ItemResourceImpl</p>
 * <p>Description: 商品管理Controller</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:21</p>
 * @author ChengTengfei
 * @version 1.0
 */
//@Service(interfaceClass = ItemResource.class, retries = 2)
@Service(interfaceClass = ItemResource.class)
@Controller
public class ItemResourceImpl implements ItemResource {

    private static final Logger log = LoggerFactory.getLogger(ItemResourceImpl.class);

    @Autowired
    private ItemService itemService;

    @Override
    public TbItem getItemById(long itemId) {
        TbItem res = null;
        try {
            log.info("根据商品ID查询商品基本信息 getItemById itemId = " + itemId);
            res = itemService.getItemById(itemId);
            log.info("根据商品ID查询商品基本信息 getItemById res = {}", JacksonUtils.objectToJson(res));
        } catch (Exception e){
            log.error("### Call ItemResourceImpl.getItemById error = {}", e);
        }
        return res;
    }

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        EasyUIDataGridResult res = null;
        try {
            log.info("商品列表信息 getItemList page = {}, rows = {}", String.valueOf(page), String.valueOf(rows));
            res = itemService.getItemList(page, rows);
            log.info("商品列表信息 getItemList res = {}", JacksonUtils.objectToJson(res));
        } catch (Exception e){
            log.error("### Call ItemResourceImpl.getItemList error = {}", e);
        }
        return res;
    }

    @Override
    public TaotaoResult addItem(TbItem item, String desc) {
        TaotaoResult res = null;
        try {
            log.info("添加商品信息 addItem item = {}, desc = {}", JacksonUtils.objectToJson(item), desc);
            res = itemService.addItem(item, desc);
            log.info("添加商品信息 addItem res = {}", JacksonUtils.objectToJson(res));
        } catch (Exception e){
            log.error("### Call ItemResourceImpl.addItem error = {}", e);
        }
        return res;
    }

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        TbItemDesc res = null;
        try {
            log.info("根据商品ID查询商品详情信息 getItemDescById itemId = " + itemId);
            res = itemService.getItemDescById(itemId);
            log.info("根据商品ID查询商品详情信息 getItemDescById res = {}", JacksonUtils.objectToJson(res));
        } catch (Exception e){
            log.error("### Call ItemResourceImpl.getItemDescById error = {}", e);
        }
        return res;
    }

}
