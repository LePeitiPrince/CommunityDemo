package spring.adog.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.adog.dto.PaginationDTO;
import spring.adog.dto.QuestionDTO;
import spring.adog.mapper.QuestionMapper;
import spring.adog.mapper.UserMapper;
import spring.adog.model.Question;
import spring.adog.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO pagination = new PaginationDTO();
        Integer count = questionMapper.count();
        pagination.setPagination(count,page,size);
        //数据库的判断
        if (page < 1){
            page = 1;
        }
        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size*(page-1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.list(offset,size);
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreatorId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);
        return pagination;
    }

    /**
     * 根据userId做分页查询
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO pagination = new PaginationDTO();
        Integer count = questionMapper.countById(userId);
        pagination.setPagination(count,page,size);
        //数据库的判断
        if (page < 1){
            page = 1;
        }
        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size*(page-1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        for (Question question : questions) {
            User user = userMapper.findUserById(question.getCreatorId());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);
        return pagination;
    }
}
