package spring.adog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.adog.dto.CommentDTO;
import spring.adog.enums.CommentTypeEnum;
import spring.adog.enums.NotificationStatusEnum;
import spring.adog.enums.NotificationTypeEnum;
import spring.adog.exception.CustomizeErrorCode;
import spring.adog.exception.CustomizeException;
import spring.adog.mapper.*;
import spring.adog.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    //spring提供的事务注解
    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null){
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            commentMapper.insert(comment);

            //增加评论数
            Comment parentComment = new Comment();
            parentComment.setId(comment.getParentId());
            parentComment.setCommentCount(1);
            commentExtMapper.incCommentCount(parentComment);

            // 创建通知
            createNotify(comment, dbComment.getCommentator(), question.getTITLE(), commentator.getNAME(), NotificationTypeEnum.REPLY_COMMENT,question.getID());
        }else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCOMMENT_COUNT(1);
            questionExtMapper.incCommentCount(question);

            // 创建通知
            createNotify(comment, question.getCREATORID(), question.getTITLE(), commentator.getNAME(), NotificationTypeEnum.REPLY_QUESTION,question.getID());
        }
    }

    private void createNotify(Comment comment, Long receiver, String outerTitle, String notifierName, NotificationTypeEnum notificationTypeEnum,Long outerId) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());
        notification.setOuterid(outerId);
        notification.setNotifier(comment.getCommentator());
        notification.setReceiver(receiver);
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTargetId(Long id, CommentTypeEnum question) {
        CommentExample example = new CommentExample();
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(question.getType());
        example.setOrderByClause("GMT_CREATE DESC");
        List<Comment> comments = commentMapper.selectByExample(example);
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        //获取去重的评论人id
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换成map对象，降低了user跟comment的匹配复杂度
        Map<Long,User> userMap = UserMapUtil.getUserMap(userIds, userMapper);

        //转化comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }

    /**
     * 创建通知
     * @param comment
     * @param receiver
     * @param notifierName
     * @param outerTitle
     * @param notificationType
     * @param outerId
     */
//    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Long outerId) {
//        Notification notification = new Notification();
//        notification.setGmtCreate(System.currentTimeMillis());
//        notification.setTypeName(notificationType.getTypeName());
//        notification.setOuterId(outerId);
//        notification.setNotifier(comment.getCommentator());
//        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
//        notification.setReceiver(receiver);
//        notification.setNotifierName(notifierName);
//        notification.setOuterTitle(outerTitle);
//        notificationMapper.insert(notification);
//    }
}
