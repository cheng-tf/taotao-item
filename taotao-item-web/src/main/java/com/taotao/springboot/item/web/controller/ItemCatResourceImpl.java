package com.taotao.springboot.item.web.controller;

import com.alibaba.dubbo.config.annotation.Service;
import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.result.EasyUITreeNode;
import com.taotao.springboot.item.export.ItemCatResource;
import com.taotao.springboot.item.service.ItemCatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>Title: ItemCatResourceImpl</p>
 * <p>Description: 商品类目管理Controller</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:40</p>
 * @author ChengTengfei
 * @version 1.0
 */
@Service(interfaceClass = ItemCatResource.class, retries = 2)
@Controller
public class ItemCatResourceImpl implements ItemCatResource{

    private static final Logger log = LoggerFactory.getLogger(ItemCatResourceImpl.class);

    @Autowired
    private ItemCatService itemCatService;

    @Override
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        List<EasyUITreeNode> res = null;
        try {
            log.info("根据父ID查询商品类目信息 getItemCatList parentId = {}", String.valueOf(parentId));
            res = itemCatService.getItemCatList(parentId);
            log.info("根据父ID查询商品类目信息 getItemCatList res = {}", JacksonUtils.objectToJson(res));
        } catch (Exception e){
            log.error("### Call ItemCatResourceImpl.getItemCatList error = {}", e);
        }
        return res;
    }
}
