package com.puffer.spider.processor;

import com.puffer.spider.pipeline.GitHubPipeline2;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 亚马逊爬虫
 *
 * @author buyi
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    private static final String URL_HELP = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr";
    private static final String URL_LIST = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bsnr_pg_\\d/146-8751176-4088033?ie=UTF8&pg=\\d";
    private static final String URL_POST = "";

    Pattern pattern = Pattern.compile("(\\d+\\.\\d+)|(\\d+)");

    @Override
    public void process(Page page) {

        //
        if (page.getUrl().regex(URL_LIST).match() || page.getUrl().regex(URL_HELP).match()) {
            //获取所有商品连接
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-right-col\"]").links().all());

            //获取所有分页的连接
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-center-div\"]/div[2]/div").links().all());
            page.setSkip(true);
        } else {
            //商品标题

            //*[@id="productTitle"]
            String title = page.getHtml().xpath("//*[@id=\"productTitle\"]/text()").toString();
            if (StringUtils.isBlank(title)) {
                //skip this page
                page.setSkip(true);
                return;
            }

            page.putField("title", title);
            String price = getPrice(page);
            page.putField("price", price);

            String rank = getRank(page);
            page.putField("rank", rank);
            page.putField("url", page.getUrl().toString());
        }

    }

    private String getPrice(Page page) {
        List<String> xpahtList = Lists.newArrayList();
        xpahtList.add("//*[@id=\"priceblock_ourprice\"]/text()");
        xpahtList.add("//*[@id=\"priceblock_saleprice\"]/text()");
        String price = "";
        for (String xpath : xpahtList) {
            price= page.getHtml().xpath(xpath).toString();
            if(StringUtils.isNotBlank(price)){
                return price;
            }
        }
        return "";
    }

    private String getRank(Page page){
        List<String> xpahtList = Lists.newArrayList();
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()");
        xpahtList.add("*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[6]/td/span/span[2]/text()");
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[8]/td/span/span[3]/text()");
        String rank = "";
        for (String xpath : xpahtList) {
            rank= page.getHtml().xpath(xpath).toString();
            if(StringUtils.isNotBlank(rank)){
                Matcher matcher = pattern.matcher(rank);
                matcher.find();
                return matcher.group();
            }
        }
        return "";
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        // Spider.create(new AmazonPageProcessor())
        //         .addUrl("https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr")
        //         .addPipeline(new GitHubPipeline2())
        //         //开启5个线程抓取
        //         .thread(5)
        //         //启动爬虫
        //         .run();
    }
}
