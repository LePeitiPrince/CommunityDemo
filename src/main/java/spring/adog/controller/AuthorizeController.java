package spring.adog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.adog.dto.AccessTokenDTO;
import spring.adog.dto.GithubUser;
import spring.adog.provider.GithubProvider;

@Controller
public class AuthorizeController {
    @Autowired
    public GithubProvider githubProvider;

    @Value("github.client.id")
    private String clientId;
    @Value("github.client.secret")
    private String secret;
    @Value("github.client.redirect_uri")
    private String redirect_id;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_id);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
