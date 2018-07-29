package com.qfedu.core.util;

import com.qfedu.core.vo.Result;

/**
 *@Author feri
 *@Date Created in 2018/7/24 14:48
 */
public class ExecuteUtils {
    public static Result getResult(int num, String msg){
        if(num>0){
            return Result.setOK(msg+"成功");
        }else{
            return Result.setError(msg+"失败");
        }
    }
}
