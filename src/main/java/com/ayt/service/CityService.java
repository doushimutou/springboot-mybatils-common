package com.ayt.service;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/522:33
 */

import com.ayt.model.City;

/**
 * @author ayt  on 20180805
 */
public interface CityService {
    /**
     * 根据id查询城市
     * @param id
     * @return
     */
    City fingById(String id) ;
}
