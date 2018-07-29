package com.qfedu.sso.controller;


import com.qfedu.core.util.CookieUtil;
import com.qfedu.core.vo.Result;
import com.qfedu.domain.user.User;
import com.qfedu.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService service;
    //注册
    @PostMapping("/useradd")
    public Result save(User user){
        return service.save(user);
    }


    //查询
    @GetMapping("/users")
    public List<User> queryAll(){
        return service.queryAll();
    }


    //登录
    @PostMapping("/userlogin")
    public Result login(String name,String password,HttpServletRequest request,HttpServletResponse response){

        Result result = service.ssoLogin(name,password);
        if (result.getCode() == 0){
            CookieUtil.setCK("userauth", result.getMsg(),response);
        }
        return result;
    }

    //检查用户登录
    @GetMapping("/usercheck")
    public Result checkLogin(HttpServletResponse response, HttpServletRequest request){

        String token = CookieUtil.getCk("userauth", request);
        Result result = service.ssoCheck(token);

        if (result.getCode() != 0){
            CookieUtil.delCK("userauth",response);
        }
        return result;
    }
    //注销
    @GetMapping("/loginout")
    public Result loginout(HttpServletRequest request,HttpServletResponse response){

        String tk=CookieUtil.getCk("userauth",request);
        Result r= service.loginOut(tk);
        if(r.getCode()==0){
            CookieUtil.delCK("userauth",response);
        }
        System.err.println("已经注销");
        return r;
    }

    //检查用户用是否重复
    @GetMapping("checkname")
    public Result checkName(String username){
        System.err.println("----------------------------->要注册的用户名为:" + username);
        return service.checkName(username);
    }






}
