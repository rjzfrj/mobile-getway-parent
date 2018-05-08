package com.qhwy.mgetway.dao.user;

import org.apache.ibatis.annotations.Mapper;

import com.qhwy.mgetway.entity.user.Muser;
@Mapper
public interface MuserMapper {

    
    Muser selectByLoginName(String loginName);


    int deleteByPrimaryKey(Long uId);

    int insert(Muser record);

    int insertSelective(Muser record);

    Muser selectByPrimaryKey(Long uId);

    int updateByPrimaryKeySelective(Muser record);

    int updateByPrimaryKey(Muser record);
}