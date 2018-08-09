package com.taotao.springboot.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.springboot.item.common.utils.IDUtils;
import com.taotao.springboot.item.common.utils.JacksonUtils;
import com.taotao.springboot.item.domain.pojo.TbItem;
import com.taotao.springboot.item.domain.pojo.TbItemDesc;
import com.taotao.springboot.item.domain.pojo.TbItemExample;
import com.taotao.springboot.item.domain.result.EasyUIDataGridResult;
import com.taotao.springboot.item.domain.result.TaotaoResult;
import com.taotao.springboot.item.mapper.TbItemDescMapper;
import com.taotao.springboot.item.mapper.TbItemMapper;
import com.taotao.springboot.item.service.ItemService;
import com.taotao.springboot.item.service.jedis.JedisClient;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * <p>Title: ItemServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: bupt.edu.cn</p>
 * <p>Created: 2018-05-05 17:01</p>
 * @author ChengTengfei
 * @version 1.0
 */
@Service
@Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.DEFAULT)
public class ItemServiceImpl implements ItemService{

    private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private JedisClient jedisClient;
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;
    @Value("${TIEM_EXPIRE}")
    private Integer TIEM_EXPIRE;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TbItem getItemById(long itemId) {
        // 查询缓存
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":BASE");
            if (StringUtils.isNotBlank(json)) {
                return JacksonUtils.jsonToPojo(json, TbItem.class);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        // 缓存中不存在，则查询数据库
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        // 添加缓存
        try {
            jedisClient.set(ITEM_INFO + ":" + itemId + ":BASE", JacksonUtils.objectToJson(item));
            jedisClient.expire(ITEM_INFO + ":" + itemId  + ":BASE", TIEM_EXPIRE);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public EasyUIDataGridResult getItemList(int page, int rows) {
        // 设置分页条件
        PageHelper.startPage(page, rows);
        TbItemExample itemExample = new TbItemExample();
        List<TbItem> itemList = itemMapper.selectByExample(itemExample);
        // 封装查询结果
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(itemList);
        return result;
    }

    @Override
    public TaotaoResult addItem(TbItem item, String desc) {
        // 插入商品数据
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);		// 商品状态，1-正常，2-下架，3-删除
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        // 插入商品描述数据
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        itemDescMapper.insert(itemDesc);
        // 基于ActiveMQ发送商品添加消息
        /*jmsTemplate.convertAndSend(new ActiveMQTopic("item-add-topic"), new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                // 发送商品ID
                return session.createTextMessage(itemId + "");
            }
        });*/
        // 基于RabbitMQ发送商品添加消息
        this.rabbitTemplate.convertAndSend("item-add","", String.valueOf(itemId));
        return TaotaoResult.ok();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public TbItemDesc getItemDescById(long itemId) {
        // 查询缓存
        try {
            String json = jedisClient.get(ITEM_INFO + ":" + itemId + ":DESC");
            if (StringUtils.isNoneBlank(json)) {
                return JacksonUtils.jsonToPojo(json, TbItemDesc.class);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        // 缓存中不存在，则查询数据库
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        // 添加缓存
        try {
            jedisClient.set(ITEM_INFO + ":" + itemId + ":DESC", JacksonUtils.objectToJson(itemDesc));
            jedisClient.expire(ITEM_INFO + ":" + itemId + ":DESC", TIEM_EXPIRE);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return itemDesc;
    }

}
