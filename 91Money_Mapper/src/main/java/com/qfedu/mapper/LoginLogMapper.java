package com.qfedu.mapper;


import java.util.List;

import com.qfedu.domain.loginlog.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginLogMapper {

    @Insert("insert into t_loginlog(ip,createtime,uid,msg) values(#{ip},#{createtime},#{uid},#{msg})")
    int insert(LoginLog record);

    @Select("select id,ip ,createtime,uid msg from t_loginlog where id=#{id}")
    LoginLog queryById(int id);

    @Select("select id,ip,createtime,uid,msg from t_loginlog")
    List<LoginLog> queryAll();
    }