package com.taotao.springboot.item.web.service;

import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.result.EasyUITreeNode;
import com.taotao.springboot.item.service.ItemCatService;
import org.junit.*;
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

    @BeforeClass
    public static void beforeClassTest() {
        log.info("单元测试开始之前初始化...");
        log.info("---------------------------------------------");
    }

    @AfterClass
    public static void afterClassTest() {
        log.info("---------------------------------------------");
        log.info("单元测试结束之后执行...");
    }

    @Before
    public void beforeTest() {
        log.info("单元测试方法开始之前执行...");
    }

    @After
    public void afterTest() {
        log.info("单元测试方法结束之后执行...");
    }

    // 根据父节点ID查询子节点列表
    @Test
    public void testGetItemCatList() {
        List<EasyUITreeNode> list =  itemCatService.getItemCatList(1L);
        log.info(JacksonUtils.objectToJson(list));
    }

}
