package com.taotao.springboot.item.export;

import com.taotao.springboot.item.domain.result.EasyUITreeNode;

import java.util.List;

/**
 * <p>Title: ItemCatResource</p>
 * <p>Description: </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:39</p>
 * @author ChengTengfei
 * @version 1.0
 */
public interface ItemCatResource {

    /**
     * 根据parentID查询商品类目列表
     */
    List<EasyUITreeNode> getItemCatList(long parentId);

}
