package com.zzh.Service;

import com.zzh.vo.Result;
import com.zzh.vo.TagVo;

import java.util.List;

public interface TagService {

    List<TagVo> findTagsByArticleId(Long articleId);

    Result hot(int limit);

    Result findAll();

    Result findAllDetail();

    Result findDetailById(Long id);

}
