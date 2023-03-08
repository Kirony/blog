package com.zzh.vo.params;

import com.zzh.vo.CategoryVo;
import com.zzh.vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;

    public String search;
}

