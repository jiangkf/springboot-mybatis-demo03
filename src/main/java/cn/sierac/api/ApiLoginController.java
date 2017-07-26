package cn.sierac.api;

import cn.sierac.entity.User;
import cn.sierac.service.UserService;
import cn.sierac.utils.CookieUtils;
import cn.sierac.utils.TokenProcessor;
import cn.sierac.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录api
 * Created by Jack on 2017/7/26.
 */
@RestController
@RequestMapping(value = "/a/api/v1")
public class ApiLoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "pack/user/login", headers = { "Accept=application/json" })
    public String login(HttpServletRequest request , HttpServletResponse response, @RequestBody String JsonParam){
        User user = JSON.parseObject(JsonParam,User.class);
        User userdb =  userService.getUserByName(user.getLoginName());
        boolean b = UserUtils.validatePassword(user.getPassword(), userdb.getPassword());
        if(!userdb.equals(null) && b){
            String cookieToken = new TokenProcessor().generateToken("Vicky", true);// 加入时间戳的令牌
            CookieUtils.setCookie(response, "cookieToken", cookieToken);
            HttpSession session = request.getSession();
            session.setAttribute("sessionToken", cookieToken);
            response.setHeader("cookieHeader", cookieToken);
            response.setStatus(200);
            return "ok/200";
        }
        response.setStatus(403);
        return "error/403";
    }



}
