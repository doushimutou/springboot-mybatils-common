package com.ayt.mapper;

import com.ayt.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    User findUserByCityId(@Param("cityId") Integer cityId);

}