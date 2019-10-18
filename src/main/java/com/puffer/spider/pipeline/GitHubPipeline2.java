package com.puffer.spider.pipeline;

import com.puffer.spider.dao.AmazonItemDao;
import com.puffer.spider.entity.AmazonItem;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;

/**
 * 亚马逊商品信息持久化
 *
 * @author buyi
 * @date 2019年10月14日 22:47:47
 * @since 1.0.0
 */
@Component
public class GitHubPipeline2 implements Pipeline {

    @Resource
    private AmazonItemDao amazonItemDao;

    @Override
    public void process(ResultItems resultItems, Task task) {
        AmazonItem amazonItem = new AmazonItem();
        amazonItem.setType("1");
        amazonItem.setTitle(resultItems.get("title"));
        amazonItem.setPrice(resultItems.get("price"));
        amazonItem.setUrl(resultItems.get("url"));
        amazonItem.setRank(resultItems.get("rank"));

        amazonItemDao.save(amazonItem);
        // System.out.println(resultItems);
    }
    // @Resource
    // private AmazonItemDao amazonItemDao;

}
