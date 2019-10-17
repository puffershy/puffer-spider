package com.puffer.spider.crawler;

import org.testng.annotations.Test;

import javax.annotation.Resource;

public class AmazonCrawlerTest extends AbstractTest {

    @Resource
    private AmazonCrawler amazonCrawler;

    @Test
    public void testAmazonCrawler() {
        String url = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bsnr_pg_1?ie=UTF8&pg=1";
        amazonCrawler.amazonCrawler(url);
    }
}