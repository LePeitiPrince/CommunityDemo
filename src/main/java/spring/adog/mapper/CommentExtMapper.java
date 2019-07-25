package spring.adog.mapper;

import spring.adog.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}