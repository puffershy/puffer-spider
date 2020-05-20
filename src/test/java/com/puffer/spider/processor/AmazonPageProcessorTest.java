package com.puffer.spider.processor;

import com.puffer.spider.crawler.AbstractTest;
import com.puffer.spider.pipeline.GitHubPipeline2;
import org.testng.annotations.Test;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

import static org.testng.Assert.*;

public class AmazonPageProcessorTest extends AbstractTest {

    @Resource
    private GitHubPipeline2 gitHubPipeline2;

    @Test
    public void test(){

        String url = "https://www.amazon.com/Best-Sellers-Kitchen-Dining-Novelty-Coffee-Mugs/zgbs/kitchen/9302388011/ref=zg_bs_pg_1?_encoding=UTF8&pg=1";
        Spider.create(new AmazonPageProcessor())
                .addUrl(url)
                .addPipeline(gitHubPipeline2)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}