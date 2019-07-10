package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.adog.dto.AccessTokenDTO;
import spring.adog.dto.GithubUser;
import spring.adog.model.User;
import spring.adog.provider.GithubProvider;
import spring.adog.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService service;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String secret;
    @Value("${github.client.redirect_uri}")
    private String redirect_id;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_id);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //登陆成功，写cookie和session;
            User user = new User();
            user.setAVATAR_URL(githubUser.getAvatar_url());
            user.setACCOUNT_ID(String.valueOf(githubUser.getId()));
            user.setNAME(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setTOKEN(token);
            user.setBIO(githubUser.getBio());
            service.createOrUpdate(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie token = new Cookie("token", null);
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:/";
    }
}
