package spring.adog.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.adog.model.Question;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmtCreate,gmtModified,creatorId,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creatorId},#{tag})")
    void insertQuestion(Question question);

    @Select("select * from question limit #{offset},#{size}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(*) from question")
    Integer count();
}
