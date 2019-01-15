package com.ayt.serviceImp;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/522:49
 */

import com.ayt.mapper.city.CityMapper;
import com.ayt.mapper.user.UserMapper;
import com.ayt.model.City;
import com.ayt.model.User;
import com.ayt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ayt  on 20180805
 */
@Service
public class UserServicesImp implements UserService{
    @Autowired(required = false)
    CityMapper cityMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public User findUserByCityId(int cityid) {
        User user=userMapper.findUserByCityId(cityid);
        System.out.println("111111111");
        City city=cityMapper.findById("1");
        user.setCity(city);
        System.out.println(user);
        return user;

    }
}
