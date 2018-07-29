package com.qfedu.mapper.user;

import com.qfedu.domain.user.OSSPo;

public interface OSSPoMapper {
    int insert(OSSPo record);

    int insertSelective(OSSPo record);
}