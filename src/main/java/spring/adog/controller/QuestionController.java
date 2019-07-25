package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.adog.dto.CommentDTO;
import spring.adog.dto.QuestionDTO;
import spring.adog.enums.CommentTypeEnum;
import spring.adog.service.CommentService;
import spring.adog.service.QuestionService;

import java.util.List;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable("id") Long id,
                           Model model){
        QuestionDTO question = questionService.getQuestionById(id);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);

        model.addAttribute("question",question);
        model.addAttribute("comments",comments);
        return "question";
    }
}
