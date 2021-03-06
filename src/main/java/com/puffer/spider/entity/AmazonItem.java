package com.puffer.spider.entity;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

import java.math.BigDecimal;

/**
 * 亚马逊商品<br>
 * 将URL中常用的字符.默认做了转义，变成了\.
 * 将"*"替换成了".*"，直接使用可表示通配符
 *
 * @author puffer
 * @date 2019年10月13日 19:40:23
 * @since 1.0.0
 */
@Data
@ExtractBy(value = "//*[@id=\"zg-right-col\"]", multi = true)
@HelpUrl({ "https://www.amazon.com/gp/new-releases/home-garden/13749881/ref=zg_bsnr_pg_\\d/146-8751176-4088033?ie=UTF8&pg=\\d" })
@TargetUrl("http://www.amazon.cn/s/ref=sr_pg_\\S+?rh=\\S+")
public class AmazonItem {
    private Integer id;

    private String spu;
    private String type;

    @ExtractBy("//*[@id=\"productTitle\"]/text()")
    private String title;

    private String description;

    @ExtractBy("//*[@id=\"priceblock_ourprice\"]/text() | //*[@id=\"priceblock_saleprice\"]/text()")
    private BigDecimal price;

    @ExtractBy("/html/head/link[10]/@href")
    private String url;

    @ExtractBy("//*[@id=\"productDetails_detailBullets_sections1\"]/tbody/tr[7]/td/span/span[2]/text()")
    private Integer rank;

    private String imageUrl;
}
