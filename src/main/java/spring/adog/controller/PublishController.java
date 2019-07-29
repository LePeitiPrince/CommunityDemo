package spring.adog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.adog.cache.TagCache;
import spring.adog.dto.QuestionDTO;
import spring.adog.mapper.QuestionMapper;
import spring.adog.model.Question;
import spring.adog.model.User;
import spring.adog.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Long id,
                       Model model){
        QuestionDTO question = questionService.getQuestionById(id);
        model.addAttribute("title",question.getTITLE());
        model.addAttribute("description",question.getDESCRIPTION());
        model.addAttribute("tag",question.getTAG());
        model.addAttribute("id",question.getID());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            @RequestParam("id") Long id,
                            HttpServletRequest request,
                            Model model){
        //后端校验工作
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());

        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空...");
            return "publish";
        }
        if (description == null || description.equals("")){
            model.addAttribute("error","内容不能为空...");
            return "publish";
        }
        if (tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        //校验标签合法性
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","输入非法标签："+invalid);
            return "publish";
        }
        //登陆验证交给拦截器
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            model.addAttribute("error","用户未登录,请登录后重试...");
            return "publish";
        }

        Question question = new Question();
        question.setTITLE(title);
        question.setDESCRIPTION(description);
        question.setTAG(tag);
        question.setCREATORID(user.getID());
        question.setID(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
