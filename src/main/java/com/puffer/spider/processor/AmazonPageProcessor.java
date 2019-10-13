package com.puffer.spider.processor;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * 亚马逊爬虫
 *
 * @author buyi
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private static final String URL_HELP = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr";
    private static final String URL_LIST = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bsnr_pg_\\d/146-8751176-4088033?ie=UTF8&pg=\\d";
    private static final String URL_POST = "";

    @Override
    public void process(Page page) {

        //
        if (page.getUrl().regex(URL_LIST).match() || page.getUrl().regex(URL_HELP).match()) {
            //获取所有商品连接
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-right-col\"]").links().all());

            //获取所有分页的连接
            page.addTargetRequests(page.getHtml().xpath("//*[@id=\"zg-center-div\"]/div[2]/div").links().all());
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
            String price = page.getHtml().xpath("//*[@id=\"priceblock_ourprice\"]/text()").toString();
            if(StringUtils.isBlank(price)){
                price = page.getHtml().xpath("//*[@id=\"priceblock_saleprice\"]/text()").toString();
            }

            if(StringUtils.isBlank(price)){
                System.out.println("价格为空");
                page.setSkip(true);
                return;
            }
            page.putField("price", price);

            page.putField("rank", page.getHtml().xpath("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()").toString());
            page.putField("url", page.getUrl().toString());
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new AmazonPageProcessor())
                .addUrl("https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr")
                .addPipeline(new JsonFilePipeline("E:\\tmp\\amazon"))
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
