package com.puffer.spider.crawler;

import com.puffer.spider.common.constants.Constants;
import com.puffer.spider.entity.GitHubRepo;
import com.puffer.spider.pipeline.GitHubPipeline;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.OOSpider;

import javax.annotation.Resource;

/**
 * GitHub爬虫
 *
 * @author puffer
 * @date 2019年10月18日 18:53:13
 * @since 1.0.0
 */
@Component
public class GitHubCrawler {
    @Resource
    private GitHubPipeline gitHubPipeline;

    public void crawler(String url) {

        OOSpider.create(
                Site.me().setUserAgent(Constants.USERAGENT),
                gitHubPipeline, GitHubRepo.class)
                .setDownloader(new HttpClientDownloader())
                .addUrl(url)
                .thread(5)
                .run();
    }
}
