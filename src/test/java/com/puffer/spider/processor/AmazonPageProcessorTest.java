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
                .addUrl("https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr")
                .addPipeline(gitHubPipeline2)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}