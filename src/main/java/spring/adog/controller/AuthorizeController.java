package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.adog.dto.AccessTokenDTO;
import spring.adog.dto.GithubUser;
import spring.adog.mapper.UserMapper;
import spring.adog.model.User;
import spring.adog.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("github.client.id")
    private String clientId;
    @Value("github.client.secret")
    private String secret;
    @Value("github.client.redirect_uri")
    private String redirect_id;

    @Autowired
    private UserMapper userMapper;

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
            //登陆成功，写cookie和session;判断用户是否在数据库已经存在，如果不存在就写入数据库
            String token = userMapper.findTokenByName(githubUser.getName());
            if (token == null) {
                User user = new User();
                user.setAccount_id(String.valueOf(githubUser.getId()));
                user.setName(githubUser.getName());
                token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setGmt_create(System.currentTimeMillis());
                user.setGmt_modified(user.getGmt_create());
                user.setBio(githubUser.getBio());
                userMapper.insertUser(user);
                response.addCookie(new Cookie("token", token));
                return "redirect:/";
            }else {
                response.addCookie(new Cookie("token", token));
                return "redirect:/";
            }
        } else {
            //登陆失败，重新登陆
            return "redirect:/";
        }
    }
}
