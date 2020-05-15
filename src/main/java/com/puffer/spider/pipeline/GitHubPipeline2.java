package com.puffer.spider.pipeline;

import com.puffer.spider.common.enums.ProductTypeEnum;
import com.puffer.spider.dao.AmazonItemDao;
import com.puffer.spider.entity.AmazonItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * 亚马逊商品信息持久化
 *
 * @author puffer
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
        amazonItem.setType(ProductTypeEnum.BALLON.name());
        amazonItem.setTitle(resultItems.get("title"));
        amazonItem.setSpu(resultItems.get("spu"));
        amazonItem.setPrice(new BigDecimal(resultItems.get("price").toString()));
        amazonItem.setUrl(resultItems.get("url"));

        amazonItem.setRank(StringUtils.isNotBlank(resultItems.get("rank")) ? Integer.valueOf(resultItems.get("rank")) : null);

        amazonItem.setDescription(resultItems.get("description"));
        amazonItem.setImageUrl(resultItems.get("imageUrl"));

        amazonItemDao.save(amazonItem);
        // System.out.println(resultItems);
    }
    // @Resource
    // private AmazonItemDao amazonItemDao;

}
