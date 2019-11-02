package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.adog.dto.NotificationDTO;
import spring.adog.enums.NotificationTypeEnum;
import spring.adog.model.User;
import spring.adog.service.NotificationService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable("id") Long id){

        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id,user);
        if (NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType() || NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterid();
        }
        return "redirect:/";
    }
}
