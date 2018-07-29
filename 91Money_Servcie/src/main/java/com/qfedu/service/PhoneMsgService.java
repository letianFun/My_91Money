package com.qfedu.service;

import com.qfedu.core.vo.Result;

public interface PhoneMsgService {

    //注册验证码
    Result sendMsg(String phone);

    //jian检验验证码是否正确
    Result checkCode(String usercode,String code);
}