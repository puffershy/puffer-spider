package com.puffer.spider.processor;

import com.puffer.spider.common.constants.PatternConstants;
import com.puffer.spider.common.util.PatterUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;
import java.util.regex.Matcher;

/**
 * 亚马逊爬虫
 *
 * @author puffer
 * @date 2019年10月13日 19:32:56
 * @since 1.0.0
 */
public class AmazonPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    // https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_nav_t_2_1266203011
    // https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=zg_bs_pg_2?_encoding=UTF8&pg=2
    // private static final String URL_HELP = "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bs_tab_t_bsnr";
    private static final String URL_HELP = "https://www.amazon.com/Best-Sellers-Toys-Games-Kids-Party-Balloons/zgbs/toys-and-games/274321011/ref=*";
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
            page.setSkip(true);
        } else {
            //商品标题
            String title = page.getHtml().xpath("//*[@id=\"productTitle\"]/text()").toString();
            if (StringUtils.isBlank(title)) {
                page.setSkip(true);
                return;
            }
            page.putField("title", title);

            //商品价格
            String price = getPrice(page);
            price =  price.replace("$","");
            page.putField("price", price);

            //排名
            String rank = getRank(page);
            rank.replace(",","");
            page.putField("rank", rank);

            //地址
            String url = page.getUrl().toString();
            page.putField("url", url);

            //spu
            String spu = page.getUrl().regex(PatternConstants.SPU).toString();
            page.putField("spu", spu);
        }

    }

    private String getPrice(Page page) {
        List<String> xpahtList = Lists.newArrayList();
        xpahtList.add("//*[@id=\"priceblock_ourprice\"]/text()");
        xpahtList.add("//*[@id=\"priceblock_saleprice\"]/text()");
        String price = "";
        for (String xpath : xpahtList) {
            price = page.getHtml().xpath(xpath).toString();
            if (StringUtils.isNotBlank(price)) {
                return price;
            }
        }
        return "";
    }

    private String getRank(Page page) {
        List<String> xpahtList = Lists.newArrayList();
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()");
        xpahtList.add("*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[6]/td/span/span[2]/text()");
        xpahtList.add("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[8]/td/span/span[3]/text()");
        String rank = "";
        for (String xpath : xpahtList) {
            rank = page.getHtml().xpath(xpath).toString();
            if (StringUtils.isNotBlank(rank)) {
                Matcher matcher = PatterUtil.instance(PatternConstants.NUMBER).matcher(rank);
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
