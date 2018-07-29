package com.qfedu.provider;

import com.qfedu.core.util.MsgUtils;
import com.qfedu.core.vo.Result;
import com.qfedu.service.PhoneMsgService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

@Service
public class PhoneMsgProvider implements PhoneMsgService {
    @Override
    public Result sendMsg(String phone) {

        try {
            String code=(new Random().nextInt(9000)+1000)+"";
            MsgUtils.sendMsgCode(phone,code);
            System.err.println("发送的验证码是:--------------->" + code);
            return Result.setOK(code);
        }catch (Exception e){
            return Result.setError("发送验证码出现异常!");
        }
    }

    @Override
    public Result checkCode(String usercode, String code) {
        System.err.println("用户的验证码是:------>" + usercode + "-------自动生成的是--------->" + code);
        if (Objects.equals(code,usercode)){
            return Result.setOK("验证码正确");
        }else {
            return Result.setError("验证码错误!");
        }

    }
}
