package com.taotao.springboot.item.web.service;

import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.pojo.TbItem;
import com.taotao.springboot.item.domain.pojo.TbItemDesc;
import com.taotao.springboot.item.domain.result.EasyUIDataGridResult;
import com.taotao.springboot.item.domain.result.TaotaoResult;
import com.taotao.springboot.item.service.ItemService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>Title: ItemServiceImplTest</p>
 * <p>Description: ItemService测试类 </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-09-01 11:17</p>
 * @author ChengTengfei
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImplTest.class);

    // 根据商品ID查询商品基本信息
    @Test
    @Sql("classpath:sql/item.sql")
    public void testGetItemById() {
        TbItem item = itemService.getItemById(1241863354654L);
        Assert.assertEquals(item.getTitle(), "百加 (100＋) V6 32G版 银润 联通3G手机 双卡双待");
        log.info(item.getTitle());
    }

    // 根据商品ID获取商品详情
    @Test
    @Sql("classpath:sql/itemDesc.sql")
    public void testGetItemDescById() throws ParseException {
        TbItemDesc itemDesc = itemService.getItemDescById(87572435462L);
        Assert.assertEquals(itemDesc.getCreated(), sdf.parse("2015-03-08 21:27:49"));
        Assert.assertEquals(itemDesc.getUpdated(), sdf.parse("2015-03-08 21:27:49"));
        log.info(sdf.format(itemDesc.getCreated()));
        log.info(sdf.format(itemDesc.getUpdated()));
    }

    // 查询商品列表
    @Test
    public void testGetItemList() {
        EasyUIDataGridResult result = itemService.getItemList(1, 30);
        log.info(String.valueOf(result.getTotal()));
        log.info(JacksonUtils.objectToJson(result.getRows()));
    }

    // 商品添加
    @Test
    public void testAddItem() {
        TbItem item = new TbItem();
        item.setTitle("百加 (100＋) V6 32G版 银润 联通3G手机 双卡双待");
        item.setSellPoint("5.5英寸FHD高清屏，八核处理器，2G+32G超大内存，1300+500万摄像头！");
        item.setPrice(88800L);
        item.setNum(465768);
        item.setImage("http://image.taotao.com/jd/f96bdaa4276f41e295a3d6f2a444fdc1.jpg");
        item.setCid(560L);
        String desc = "<table cellpadding=\\\"0\\\" cellspacing=\\\"0\\\" align=\\\"center\\\" border=\\\"0\\\" width=\\\"750\\\"> \\n <tbody> \\n  <tr> \\n   <td><img src=\\\"http://img30.360buyimg.com/erpWareDetail/nfda67aH5UbSj0ZR.jpg\\\" alt=\\\"\\";
        TaotaoResult result = itemService.addItem(item, desc);
        log.info(JacksonUtils.objectToJson(result));
    }

}
