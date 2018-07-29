package com.qfedu.mapper.user;

import com.qfedu.domain.user.LoginLog;
import com.qfedu.domain.user.LoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LoginLogMapper {
    long countByExample(LoginLogExample example);

    int deleteByExample(LoginLogExample example);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    List<LoginLog> selectByExample(LoginLogExample example);

    int updateByExampleSelective(@Param("record") LoginLog record, @Param("example") LoginLogExample example);

    int updateByExample(@Param("record") LoginLog record, @Param("example") LoginLogExample example);
}