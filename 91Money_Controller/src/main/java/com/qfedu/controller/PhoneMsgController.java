package com.qfedu.controller;

import com.qfedu.core.vo.Result;
import com.qfedu.service.PhoneMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class PhoneMsgController {

    @Autowired
    private PhoneMsgService servicer;

    @GetMapping("sendmsg")
    public Result senMsg(String phone, HttpSession session){
        System.err.println("要发送验证码的手机号是:----------------->" + phone);
       Result result= servicer.sendMsg(phone);
        if (result.getCode() == 0){
            session.setAttribute("code",result.getMsg());
            return Result.setOK("验证发送成功!!!");
        }
        return result;
    }

    @GetMapping("checkmsg")
    public Result checkMsg(String usercode,HttpSession session){
        System.err.println("-----------进入到检查验证码---------------");
        return servicer.checkCode(usercode,(String) session.getAttribute("code"));
    }

}
