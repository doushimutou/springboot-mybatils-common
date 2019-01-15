package com.ayt.mapper.city;

import com.ayt.model.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CityMapper {
    int insert(City record);

    int insertSelective(City record);
//    List<City> getCity();
    City  findById(@Param("id")String id);
}