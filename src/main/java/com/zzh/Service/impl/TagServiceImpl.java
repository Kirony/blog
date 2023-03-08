package com.zzh.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zzh.Service.TagService;
import com.zzh.dao.mapper.TagMapper;
import com.zzh.dao.pojo.Article;
import com.zzh.dao.pojo.Category;
import com.zzh.dao.pojo.Tag;
import com.zzh.vo.ArticleVo;
import com.zzh.vo.Result;
import com.zzh.vo.TagVo;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    private List<TagVo> copyList(List<Tag> records) {
        ArrayList<TagVo> tagVos = new ArrayList<>();
        for (Tag record : records) {
            tagVos.add(copy(record));
        }
        return tagVos;
    }
    private TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);

        return tagVo;
    }

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {

        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);

        return copyList(tags);
    }

    @Override
    public Result hot(int limit) {
        /*
        * 1.最热标签 这个标签下的文章数量是最多的
        * 2.查询，根据tag_id分组，从大到小，取limit个值
        * */
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);

        if(CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        /*
        * 从上面查询到的tagids的list中循环遍历tags*/
        List<Tag> tagList= tagMapper.findTagsByTagIds(tagIds);

        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId,Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<>());
        return Result.success(copyList(tags));
    }

    @Override
    public Result findDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }
}
