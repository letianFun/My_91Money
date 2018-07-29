package com.qfedu.sso.service;

import com.qfedu.core.vo.Result;
import com.qfedu.domain.user.User;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {

    //新增
    Result save(User user);

    //查询
    List<User> queryAll();

    User queryByName(String name);


    //登录
    Result ssoLogin(String name,String password);
    //检查令牌
    Result ssoCheck(String token);

    //注销账户
    Result loginOut(String token);

    //注册检查用户名
    Result checkName(String name);

}
