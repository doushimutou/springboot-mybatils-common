package com.ayt.serviceImp;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/522:49
 */


import com.ayt.mapper.UserMapper;
import com.ayt.model.User;
import com.ayt.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author ayt  on 20180805
 */
@Component
public class UserServicesImp implements UserService{

    @Resource
    UserMapper userMapper;

//    @Override
//    public int insert(User record) {
//       return userMapper.insert(record);
//    }

    @Override
    @Transactional
    @CachePut(value = "record" ,key="'redis_user_' + #result.id" )
    public User insert(User record) {
        try {
             userMapper.insert(record);
             return record;
        }catch (Exception e){
            throw e;
        }
    }


    @Override
    public int insertSelective(User record) {
        return 0;
    }

//    @Override
//    public User findUserByCityId(int cityid) {
//        User user=userMapper.findUserByCityId(cityid);
//        return user;
//    }
}
