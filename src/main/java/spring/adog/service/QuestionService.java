package spring.adog.service;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.adog.dto.PaginationDTO;
import spring.adog.dto.QuestionDTO;
//import spring.adog.exception.CustomizeErrorCode;
//import spring.adog.exception.CustomizeException;
import spring.adog.exception.CustomizeErrorCode;
import spring.adog.exception.CustomizeException;
import spring.adog.mapper.QuestionMapper;
import spring.adog.mapper.UserMapper;
import spring.adog.model.Question;
import spring.adog.model.QuestionExample;
import spring.adog.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    private int update;

    //封装offset
    public Integer getOffset(Integer page,Integer size,PaginationDTO pagination){
        //数据库的判断
        if (page < 1){
            page = 1;
        }
        if (page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size*(page-1);
        return offset;
    }

    //封装QuestionDTOList
    public List<QuestionDTO> getQuestionDTOList(List<Question> questions){
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCREATORID());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUSER(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO pagination = new PaginationDTO();
        QuestionExample example = new QuestionExample();
        Integer count = (int) questionMapper.countByExample(example);
        pagination.setPagination(count,page,size);

        Integer offset = getOffset(page,size,pagination);
        QuestionExample paginationExample = new QuestionExample();
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(paginationExample,new RowBounds(offset,size ));
        List<QuestionDTO> questionDTOList = getQuestionDTOList(questions);
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
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCREATORIDEqualTo(userId);
        Integer count = (int)questionMapper.countByExample(example);
        pagination.setPagination(count,page,size);

        Integer offset = getOffset(page,size,pagination);

        QuestionExample paginationByIdExample = new QuestionExample();
        paginationByIdExample.createCriteria()
                .andCREATORIDEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(paginationByIdExample,new RowBounds(offset,size));
        List<QuestionDTO> questionDTOList = getQuestionDTOList(questions);
        pagination.setQuestions(questionDTOList);
        return pagination;
    }

    public QuestionDTO getQuestionById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCREATORID());
        questionDTO.setUSER(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getID() == null){
            //创建
            question.setGMTCREATE(System.currentTimeMillis());
            question.setGMTMODIFIED(question.getGMTCREATE());
            questionMapper.insert(question);
        }else {
            //更新
            Question record = new Question();
            record.setGMTMODIFIED(System.currentTimeMillis());
            record.setTITLE(question.getTITLE());
            record.setDESCRIPTION(question.getDESCRIPTION());
            record.setTAG(question.getTAG());

            QuestionExample example = new QuestionExample();
            example.createCriteria()
                    .andIDEqualTo(question.getID());
            int update = questionMapper.updateByExampleSelective(record, example);
            if (update != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

}
