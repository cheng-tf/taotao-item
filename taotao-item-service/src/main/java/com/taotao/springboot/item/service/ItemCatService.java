package com.taotao.springboot.item.service;

import com.taotao.springboot.item.domain.result.EasyUITreeNode;

import java.util.List;

/**
 * <p>Title: ItemCatService</p>
 * <p>Description: 商品类目管理接口</p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 16:57</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface ItemCatService {

    /**
     * 根据parentID查询商品类目列表
     */
    List<EasyUITreeNode> getItemCatList(long parentId);

}
