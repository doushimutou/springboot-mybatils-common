package com.ayt;

import com.alibaba.fastjson.JSONObject;
import com.ayt.elasticsearch.DemoDTO;
import com.ayt.elasticsearch.ESutils;
import com.ayt.elasticsearch.SearchDTO;
import com.ayt.utils.lambdaUtil.test;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Description
 * Author ayt  on
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisDemoApplication.class)
public class EsTest {
    private static final Logger logger = LoggerFactory.getLogger(test.class);
    @Resource
    ESutils eSutils;
    @Test
    public void testEs(){
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setAddress("杭州人力资源产业园");
        demoDTO.setCityId(12346);
       IndexResponse response= eSutils.addIndex(demoDTO);
        logger.info(JSONObject.toJSONString(response));
    }

    @Test
    public void testQueryES(){
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.termQuery("cityId","12346"));
        SearchDTO searchDTO = new SearchDTO();
//        searchDTO.setRouting("1");
        searchDTO.setIndex("city");
        searchDTO.setType("demoDTO");
        searchDTO.setQueryBuilder(boolQueryBuilder);
        eSutils.search(searchDTO);
        logger.info(JSONObject.toJSONString(eSutils.search(searchDTO)));
    }

}
