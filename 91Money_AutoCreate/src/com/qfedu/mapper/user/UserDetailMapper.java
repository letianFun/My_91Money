package com.qfedu.mapper.user;

import com.qfedu.domain.user.UserDetail;
import com.qfedu.domain.user.UserDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDetailMapper {
    long countByExample(UserDetailExample example);

    int deleteByExample(UserDetailExample example);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    List<UserDetail> selectByExampleWithBLOBs(UserDetailExample example);

    List<UserDetail> selectByExample(UserDetailExample example);

    int updateByExampleSelective(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByExampleWithBLOBs(@Param("record") UserDetail record, @Param("example") UserDetailExample example);

    int updateByExample(@Param("record") UserDetail record, @Param("example") UserDetailExample example);
}