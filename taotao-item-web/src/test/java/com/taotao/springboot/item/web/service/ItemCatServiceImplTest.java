package com.taotao.springboot.item.web.service;

import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.result.EasyUITreeNode;
import com.taotao.springboot.item.service.ItemCatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: ItemCatServiceImplTest</p>
 * <p>Description: ItemCatService测试类 </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-09-01 11:57</p>
 * @author ChengTengfei
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemCatServiceImplTest {

    @Autowired
    private ItemCatService itemCatService;

    private static Logger log = LoggerFactory.getLogger(ItemCatServiceImplTest.class);

    // 根据父节点ID查询子节点列表
    @Test
    public void testGetItemCatList() {
        List<EasyUITreeNode> list =  itemCatService.getItemCatList(1L);
        log.info(JacksonUtils.objectToJson(list));
    }

}
