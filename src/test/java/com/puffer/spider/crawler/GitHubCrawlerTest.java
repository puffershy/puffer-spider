package com.puffer.spider.crawler;

import org.testng.annotations.Test;

import javax.annotation.Resource;

public class GitHubCrawlerTest extends AbstractTest {

    @Resource
    private GitHubCrawler gitHubCrawler;

    @Test
    public void testCrawler() {
        String url = "https://github.com/search?q=amazon-spider";
        gitHubCrawler.crawler(url);

    }
}