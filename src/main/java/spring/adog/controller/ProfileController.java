package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import spring.adog.dto.PaginationDTO;
import spring.adog.model.User;
//import spring.adog.service.NotificationService;
import spring.adog.service.NotificationService;
import spring.adog.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable("action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,
                          Model model){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
            //根据登陆userId查询question
            PaginationDTO paginationDTO = questionService.list(user.getID(), page, size);
            model.addAttribute("pagination", paginationDTO);
        }else if ("replies".equals(action)){
            PaginationDTO paginationDTO = notificationService.list(user.getID(), page, size);
            Long unreadCount = notificationService.unreadCount(user.getID());
            model.addAttribute("pagination",paginationDTO);
            model.addAttribute("unreadCount",unreadCount);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }
}
