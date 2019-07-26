package spring.adog.mapper;

import spring.adog.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectByRelated(Question question);
}