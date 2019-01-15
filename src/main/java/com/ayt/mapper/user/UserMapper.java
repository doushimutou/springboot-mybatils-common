package com.ayt.mapper.user;

import com.ayt.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User findUserByCityId(@Param("cityid") int cityid);
}