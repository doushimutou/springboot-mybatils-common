package com.ayt.elasticsearch;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    public List<DemoDTO> search(SearchDTO searchDTO)  {

        SearchRequestBuilder searchRequestBuilder = esClient.prepareSearch(searchDTO.getIndex())
                .setTypes(searchDTO.getType())
                .setQuery(searchDTO.getQueryBuilder())
                .setRouting(searchDTO.getRouting())
                .setFetchSource(searchDTO.getIncludes(),searchDTO.getExcludes());
        //添加排序
//        searchDTO.getSortBuilders().forEach(fieldSortBuilder -> searchRequestBuilder.addSort(fieldSortBuilder) );
        //form
//        searchRequestBuilder.setFrom((searchDTO.getCurrentPage()-1) * searchDTO.getPagesize());
        //size
//        searchRequestBuilder.setSize(searchDTO.getPagesize());
        SearchResponse response = searchRequestBuilder.get();

        return getResulyFromResponse(response);
    }

    //从searchResponse中取出自定义的对象，即_source的内容
    public List<DemoDTO> getResulyFromResponse(SearchResponse searchResponse) {
        List<DemoDTO> demoDTOList = new ArrayList<>();

        searchResponse.getHits().forEach(hit->
                {
                    Map<String,Object> map= hit.getSourceAsMap();
                    DemoDTO demoDTO = null;
                    try {
                        demoDTO = converterTo(map);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    demoDTOList.add(demoDTO);}
                );

        return demoDTOList;
    }
    public DemoDTO converterTo(Map<String,Object> map) throws InvocationTargetException, IllegalAccessException {
        DemoDTO demoDTO = new DemoDTO();
        BeanUtils.populate(demoDTO,map);
        return demoDTO;
    }





}
