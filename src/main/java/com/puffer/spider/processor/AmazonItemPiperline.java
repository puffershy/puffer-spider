package com.puffer.spider.processor;

import com.puffer.spider.dao.AmazonItemDao;
import com.puffer.spider.entity.AmazonItem;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;

import javax.annotation.Resource;

/**
 * 亚马逊商品信息持久化
 *
 * @author puffer
 * @date 2019年10月14日 22:47:47
 * @since 1.0.0
 */
@Component
public class AmazonItemPiperline implements PageModelPipeline<AmazonItem> {
    @Resource
    private AmazonItemDao amazonItemDao;

    @Override
    public void process(AmazonItem amazonItem, Task task) {
        amazonItemDao.save(amazonItem);
    }
}
