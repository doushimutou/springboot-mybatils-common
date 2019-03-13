package com.ayt.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description
 * Author ayt  on
 */
@Component
public class ESutils {

    String index_name="city";
    String index_type="demoDTO";

    @Autowired
    TransportClient esClient;

    public IndexResponse addIndex(DemoDTO demoDTO){
        String jsonStr = JSONObject.toJSONString(demoDTO);

       return esClient.prepareIndex(index_name,index_type)
                .setSource(jsonStr, XContentType.JSON)
                .setRouting(demoDTO.getCityId().toString())
                .get();

    }
}
