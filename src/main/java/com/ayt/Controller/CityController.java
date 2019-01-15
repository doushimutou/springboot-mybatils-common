package com.ayt.Controller;/**
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @author ayt
 * @date 2018/8/521:37
 */

import com.ayt.mapper.city.CityMapper;
import com.ayt.mapper.user.UserMapper;
import com.ayt.model.City;
import com.ayt.model.User;
import com.ayt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ayt  on 20180805
 */
@RestController
public class CityController {
    @Autowired(required = false)
    UserService userService;
    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired(required = false)
    CityMapper cityMapper;
    /**
     * 根据ID获取city
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    public User findById(@RequestParam(value = "cityid", required = true) int cityid) {
        return userService.findUserByCityId(cityid);
    }


    @RequestMapping(value = "/api/city12", method = RequestMethod.GET)
    public User findById12(@RequestParam(value = "cityid", required = true) int cityid) {

        return userService.findUserByCityId(cityid);
    }

    /**
     * 测试通过
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/city1", method = RequestMethod.GET)
    public City findById1(@RequestParam(value = "id", required = true) String id) {
        return cityMapper.findById(id);
    }
}
