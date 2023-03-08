package com.zzh.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzh.dao.mapper.ArticleMapper;
import com.zzh.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    /**
     * 操作在线程池中进行
     * 多线程进行更新操作防止读操作阻塞缓慢
     * @param articleMapper
     * @param article
     */
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article){

        Integer viewCounts = article.getViewCounts();
        Article articleupdate  = new Article();
        articleupdate.setViewCounts(viewCounts + 1);
        LambdaQueryWrapper<Article> queryWrapper  = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId,article.getId());
        //乐观锁
        queryWrapper.eq(Article::getViewCounts,viewCounts);
        articleMapper.update(articleupdate,queryWrapper);
        // update article set view_count=100 where view_count=99 and id =111
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
