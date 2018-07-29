package com.qfedu.mapper;


import java.util.List;

import com.qfedu.domain.oplog.OpLog;
import org.apache.ibatis.annotations.Param;

public interface OpLogMapper {


    int insert(OpLog record);


    List<OpLog> selectByExampleWithBLOBs(OpLog example);

}