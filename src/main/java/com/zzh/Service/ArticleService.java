package com.zzh.Service;

import com.zzh.vo.Result;
import com.zzh.vo.params.ArticleParam;
import com.zzh.vo.params.PageParams;

import java.util.List;

public interface ArticleService {

    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);
    /*
    * 最热文章
    * */
    Result hotArticle(int limit);
    /*
     * 最新文章
     * */
    Result newArticle(int limit);

    Result listArchives();

    /**
     * 查看文章详情
     * @param articleId
     * @return
     */
    Result findArticleById(Long articleId);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);

    Result searchArticle(String search);

}
