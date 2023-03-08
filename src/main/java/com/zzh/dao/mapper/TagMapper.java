package com.zzh.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzh.dao.pojo.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

//    根据文章id查询标签列表
    List<Tag> findTagsByArticleId(Long articleId);

    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);

}
