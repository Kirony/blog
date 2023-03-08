package com.zzh.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zzh.Service.CommentsService;
import com.zzh.Service.SysUserService;
import com.zzh.dao.mapper.ArticleMapper;
import com.zzh.dao.mapper.CommentMapper;
import com.zzh.dao.pojo.Article;
import com.zzh.dao.pojo.Comment;
import com.zzh.dao.pojo.SysUser;
import com.zzh.util.UserThreadLocal;
import com.zzh.vo.CommentVo;
import com.zzh.vo.Result;
import com.zzh.vo.UserVo;
import com.zzh.vo.params.CommentParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result commentsByArticleId(Long id) {
    /**
     * 1. 根据文章id 查询 评论列表 从 comment 表中查询
     * 2. 根据作者的id 查询作者的信息
     * 3. 判断 如果 level = 1 要去查询它有没有子评论
     * 4. 如果有 根据评论id 进行查询 （parent_id）
     */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        //select * from comments where id = ? and level = 1
        List<CommentVo> commentVoList = copylist(comments);
        return Result.success(commentVoList);
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setAuthorId(sysUser.getId());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if(parent == null || parent == 0) {
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentMapper.insert(comment);
        UpdateWrapper<Article> updateWrapper = Wrappers.update();
        updateWrapper.eq("id",comment.getArticleId());
        updateWrapper.setSql(true,"comment_counts=comment_counts+1");
        //update article set comment_counts=comment_counts+1 where id=?
        articleMapper.update(null, updateWrapper);

        CommentVo commentVo = copy(comment);
        return Result.success(commentVo);
    }

    private List<CommentVo> copylist(List<Comment> comments) {
        List<CommentVo> commentVoArrayList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoArrayList.add(copy(comment));
        }
        return commentVoArrayList;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVoById = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVoById);
        //子评论
        Integer level = comment.getLevel();
        if(level == 1) {
            Long id = comment.getId();
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        //toUser
        if(level > 1){
            Long toUid = comment.getToUid();
            UserVo toUserVo  = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
//        再一次出现sql语句出错
        queryWrapper.eq(Comment::getParentId, id);
        queryWrapper.eq(Comment::getLevel,2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return copylist(comments);
    }
}
