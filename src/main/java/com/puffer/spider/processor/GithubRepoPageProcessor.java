package com.puffer.spider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * https://gitee.com/tanweijie/amazon-spider.git
 *
 * @author buyi
 * @date 2019年10月12日 19:24:19
 * @since
 */
public class GithubRepoPageProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        String author = page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString();
        page.putField("author", author);
        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        if (page.getResultItems().get("name") == null) {
            //skip this page
            // page.setSkip(true);
        }
        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        // System.setProperty("javax.net.debug", "all");
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/puffershy/puffer-spider")
                .addPipeline(new JsonFilePipeline("E:\\spider\\")).thread(5).run();
    }
}
