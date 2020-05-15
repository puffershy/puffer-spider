package com.puffer.spider.dao;

import com.puffer.spider.crawler.AbstractTest;
import com.puffer.spider.entity.AmazonItem;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static org.testng.Assert.*;

public class AmazonItemDaoTest extends AbstractTest {

    @Resource
    private AmazonItemDao amazonItemDao;

    @Test
    public void testSave() {

        AmazonItem amazonItem = new AmazonItem();
        amazonItem.setSpu("123");
        amazonItem.setType("1");
        amazonItem.setTitle("teset");
        // amazonItem.setPrice(19.26f);
        amazonItem.setUrl("teset");
        amazonItem.setRank(123);

        amazonItemDao.save(amazonItem);
    }
}