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
        Spider.create(new AmazonPageProcessor())
                .addUrl("https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_nav_t_2_1266203011")
                .addPipeline(gitHubPipeline2)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}