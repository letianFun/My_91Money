package com.qfedu.mapper;

import com.qfedu.domain.osspo.OSSPo;
public interface OSSPoMapper {



    int insert(OSSPo record);

    int insertSelective(OSSPo record);
}