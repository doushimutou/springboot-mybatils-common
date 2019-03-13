package com.ayt.dao;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/522:49
 */

import com.ayt.model.User;

/**
 * @author ayt  on 20180805
 */
public interface UserService {

    User insert(User record);
    int insertSelective(User record);
//    User findUserByCityId(int cityid);



}
