package com.zzh.Service;

import com.zzh.vo.Result;
import com.zzh.vo.params.CommentParam;

public interface CommentsService {

    /**
     * 根据文章id去查询评论列表
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    /**
     * 发布评论
     * @param commentParam
     * @return
     */
    Result comment(CommentParam commentParam);
}
