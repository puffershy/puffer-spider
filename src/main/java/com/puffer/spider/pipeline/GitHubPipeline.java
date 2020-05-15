package com.puffer.spider.pipeline;

import com.puffer.spider.dao.AmazonItemDao;
import com.puffer.spider.entity.AmazonItem;
import com.puffer.spider.entity.GitHubRepo;
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
public class GitHubPipeline implements PageModelPipeline<Object> {
    // @Resource
    // private AmazonItemDao amazonItemDao;

    @Override
    public void process(Object gitHubRepo, Task task) {
        // amazonItemDao.save(amazonItem);
        System.out.println(gitHubRepo);
    }
}
