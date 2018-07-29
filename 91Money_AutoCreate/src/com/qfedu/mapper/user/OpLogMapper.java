package com.qfedu.mapper.user;

import com.qfedu.domain.user.OpLog;
import com.qfedu.domain.user.OpLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpLogMapper {
    long countByExample(OpLogExample example);

    int deleteByExample(OpLogExample example);

    int insert(OpLog record);

    int insertSelective(OpLog record);

    List<OpLog> selectByExampleWithBLOBs(OpLogExample example);

    List<OpLog> selectByExample(OpLogExample example);

    int updateByExampleSelective(@Param("record") OpLog record, @Param("example") OpLogExample example);

    int updateByExampleWithBLOBs(@Param("record") OpLog record, @Param("example") OpLogExample example);

    int updateByExample(@Param("record") OpLog record, @Param("example") OpLogExample example);
}