package com.ayt.mapper;

import com.ayt.model.City;
import org.apache.ibatis.annotations.Param;

public interface CityMapper {
    int insert(City record);

    int insertSelective(City record);

    City findById(@Param("id") String id);
}