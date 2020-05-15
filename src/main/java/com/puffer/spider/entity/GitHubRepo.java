package com.puffer.spider.entity;

import lombok.Data;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * github项目
 *
 * @author puffer
 * @date 2019年10月18日 18:55:35
 * @since 1.0.0
 */

@Data
@HelpUrl({ "https://github\\.com/search?p=\\d&q=amazon-spider&type=Repositories" })
@ExtractBy(value = "//*[@id=\"js-pjax-container\"]/div/div[3]/div")
@TargetUrl("https://github\\.com/\\w+/\\w+")
public class GitHubRepo {
    @ExtractBy("//*[@id=\"js-repo-pjax-container\"]/div[2]/div/h1/span[1]/a/text()")
    private String author;
    @ExtractBy("//*[@id=\"js-repo-pjax-container\"]/div[2]/div/h1/strong/a/text()")
    private String name;

    private String url;
}
