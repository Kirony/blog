package com.zzh.controller;

import com.zzh.Service.ArticleService;
import com.zzh.Service.impl.ArticleServiceImpl;
import com.zzh.common.aop.LogAnnotation;
import com.zzh.dao.pojo.SysUser;
import com.zzh.util.UserThreadLocal;
import com.zzh.vo.ArticleVo;
import com.zzh.vo.Result;
import com.zzh.vo.params.ArticleParam;
import com.zzh.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping
    @LogAnnotation(module = "文章",operator="获取文章列表")
    public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listArticle(pageParams);
    }
/*
* 首页card 最热文章*/
    @PostMapping("hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }
    /*
     * 首页card 最新文章*/
    @PostMapping("new")
    public Result newArticle(){
        int limit = 5;
        return articleService.newArticle(limit);
    }
    /*
     * 首页card 归档文章*/
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam){
        return articleService.publish(articleParam);
    }

    /**
     * 文章搜索
     * @param articleParam
     * @return
     */
    @PostMapping("search")
    public Result search(@RequestBody ArticleParam articleParam){
        //搜索接口
        String search = articleParam.getSearch();
        return articleService.searchArticle(search);
    }

    @PostMapping("{id}")
    public Result articleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }
}
