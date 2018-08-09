package com.taotao.springboot.item.service.impl;

import com.taotao.springboot.item.mapper.TbItemCatMapper;
import com.taotao.springboot.item.domain.pojo.TbItemCat;
import com.taotao.springboot.item.domain.pojo.TbItemCatExample;
import com.taotao.springboot.item.domain.result.EasyUITreeNode;
import com.taotao.springboot.item.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ItemCatServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:09</p>
 * @author ChengTengfei
 * @version 1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.DEFAULT)
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<EasyUITreeNode> getItemCatList(long parentId) {
        // 根据父节点ID查询子节点列表
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        // 转化为EasyUITreeNode列表
        List<EasyUITreeNode> resultList = new ArrayList<>();
        for (TbItemCat itemCat : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(itemCat.getId());
            node.setText(itemCat.getName());
            // 若节点下有子节点“closed”，如果没有子节点“open”
            node.setState(itemCat.getIsParent() ? "closed" : "open");
            resultList.add(node);
        }
        return resultList;
    }

}