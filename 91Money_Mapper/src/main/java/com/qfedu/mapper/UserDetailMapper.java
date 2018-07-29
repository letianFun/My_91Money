package com.qfedu.mapper;


import java.util.List;
import com.qfedu.domain.userdetail.UserDetail;

public interface UserDetailMapper {

    int insert(UserDetail userDetail);

    int queryById(UserDetail userDetail);

    List<UserDetail> queryAll(UserDetail userDetail);

    int updateById(UserDetail userDetail);
}