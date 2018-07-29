package com.qfedu.sso.provider;

import com.alibaba.fastjson.JSON;
import com.qfedu.core.redis.JedisUtil;
import com.qfedu.core.util.ExecuteUtils;

import com.qfedu.core.util.IdGenerator;
import com.qfedu.core.vo.Result;
import com.qfedu.domain.user.User;
import com.qfedu.mapper.UserMapper;
import com.qfedu.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserProvider implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private JedisUtil jedisUtil;

    @Autowired
    private  IdGenerator idGenerator;


    @Override
    public Result save(User user) {
        return ExecuteUtils.getResult(mapper.insert(user),"新增用户");
    }

    @Override
    public List<User> queryAll() {
        return mapper.queryAll();
    }

    @Override
    public User queryByName(String name) {
        return mapper.queryByName(name);
    }

    //登录
    @Override
    public Result ssoLogin(String name, String password) {
        //没有token 第一次登陆
        User user = mapper.queryByName(name);
        if (user != null){
            if (Objects.equals(password,user.getPassword())){
                //登录成功，生成唯一令牌
                long tk=idGenerator.nextId();
                //令牌存储到Redis,key:usertoken+令牌值, value:登录用户信息的json
                String value = JSON.toJSONString(user);
                System.err.println(value);
                jedisUtil.addStr("usertoken:"+tk,value);
                //设置有效期，默认是30分钟
                jedisUtil.expire("usertoken:"+tk,TimeUnit.MINUTES,30);
                //返回结束
                return new Result(0,tk+"",user);
            }else{
                return Result.setError("密码输入错误");
            }
        }else {
            return Result.setError("用户名不存在");
        }
    }

    @Override
    public Result ssoCheck(String token) {

        if (jedisUtil.isKey("usertoken:"+token)){
            //存在令牌就刷新时间
            jedisUtil.expire("usertoken:"+token,TimeUnit.MINUTES,30);
            return new Result(0,"存在",JSON.parseObject(jedisUtil.getStr("usertoken:"+token),User.class));
        }else {
            return Result.setError("不存在");
        }
    }

    @Override
    public Result loginOut(String token) {
        //移除Redis
        jedisUtil.delKey("usertoken:" + token);
        return Result.setOK("注销成功");
    }

    @Override
    public Result checkName(String name) {
        User user = mapper.queryByName(name);
        if( user == null){
            return Result.setOK("用户名可用");
        }else{
            return Result.setOK("用户名不可用");
        }
    }
}

