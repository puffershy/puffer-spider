package com.puffer.spider.entity;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 亚马逊商品<br>
 *     将URL中常用的字符.默认做了转义，变成了\.
 * 将"*"替换成了".*"，直接使用可表示通配符
 *
 * @author buyi
 * @date 2019年10月13日 19:40:23
 * @since 1.0.0
 */
@Data
@ExtractBy(value="//*[@id=\"zg-right-col\"]",multi =true)
@HelpUrl({"",""})
@TargetUrl("http://www.amazon.cn/s/ref=sr_pg_\\S+?rh=\\S+")
public class AmazonItem {
    private Integer id;
    private String type;

    @ExtractBy("//*[@id=\"productTitle\"]/text()")
    private String title;

    @ExtractBy("//*[@id=\"priceblock_ourprice\"]/text() | //*[@id=\"priceblock_saleprice\"]/text()")
    private String price;

    @ExtractBy("/html/head/link[10]/@href")
    private String url;

    @ExtractBy("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()")
    private Integer rank;
}
