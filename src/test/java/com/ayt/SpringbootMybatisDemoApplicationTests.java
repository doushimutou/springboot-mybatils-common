package com.ayt;


import com.alibaba.fastjson.JSONObject;
import com.ayt.mapper.CityMapper;
import com.ayt.mapper.UserMapper;
import com.ayt.model.City;
import com.ayt.model.User;
import com.ayt.redis.CacheService;
import com.ayt.dao.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisDemoApplication.class)
public class SpringbootMybatisDemoApplicationTests {


    @Autowired
    CacheService cacheService;
    @Autowired
    UserService userService;
    @Autowired(required = false)
    CityMapper cityMapper;
    @Autowired
    UserMapper userMapper;


    @Test
    public void contextLoads() {
        User user = new User();
        user.setCityid(1);
        user.setUserName("都是木头");
        user.setDescription("都是好人");
        cacheService.putCache("用户", user);
        Object object = cacheService.getCache("用户");
        System.out.println(JSONObject.toJSONString(object));

        cacheService.deleteCache("用户");
        System.out.println(JSONObject.toJSONString(cacheService.getCache("用户")));
    }

    @Test
    public void test1() {
//		User user =userService.findUserByCityId(1);
//		System.out.println(JSONObject.toJSON(user));
    }

    @Test
    public void test2() {
        City city = new City();
        city.setCityName("杭州");
        city.setDescription("美丽的城市");
        city.setProvinceId(23);
        cityMapper.insert(city);
    }

    @Test
    public void test3() {
        User user = new User();
        user.setCityid(1);
        user.setUserName("都是木头");
        user.setDescription("他是个男孩");
        userMapper.insert(user);

    }

    @Test
    public void insertUserCache() {
        User user = new User();
        user.setCityid(1);
        user.setUserName("他大舅");
        user.setDescription("都是他舅");
//		userMapper.insert(user);
        user = userService.insert(user);
        Object o = cacheService.getCache(String.valueOf(user.getId()));
        System.out.println(JSONObject.toJSON(o));
    }

    @Test
    public void testcase() {
        Object o = cacheService.getCache("redis_user_5");
        System.out.println(JSONObject.toJSON(o));
    }


}
