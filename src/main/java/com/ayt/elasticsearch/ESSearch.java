//package com.ayt.elasticsearch;
//
//import org.apache.poi.ss.formula.functions.T;
//import org.elasticsearch.action.search.SearchRequestBuilder;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.search.SearchHit;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Description
// * Author ayt  on
// */
//@Component
//public class ESSearch {
//
//    @Autowired
//    TransportClient client;
//
//
//    public  List<DemoDTO>  search(SearchDTO searchDTO) throws InvocationTargetException, IllegalAccessException {
//
//        //设置查询的索引、类型、查询体
//        SearchRequestBuilder searchRequestBuilder= client.prepareSearch(searchDTO.getIndex())
//                .setSearchType(searchDTO.getType()).setQuery(searchDTO.getQueryBuilder());
//        //设置路由
//        searchRequestBuilder.setRouting(searchDTO.getRouting());
//        //设置输出字段
//        searchRequestBuilder.setFetchSource(searchDTO.getIncludes(),searchDTO.getExcludes());
//        //从那一条开始读
//        searchRequestBuilder.setFrom((searchDTO.getCurrentPage()-1)* searchDTO.getPagesize());
//        //设置分页大小
//        searchRequestBuilder.setSize(searchDTO.getPagesize());
//
//        SearchResponse searchResponse = searchRequestBuilder.get();
//
//        List<DemoDTO> demoDTOList=getResulyFromResponse(searchResponse);
//        return demoDTOList;
//
//    }
//
//    //从searchResponse中取出自定义的对象，即_source的内容
//    public List<DemoDTO>  getResulyFromResponse(SearchResponse searchResponse) throws InvocationTargetException, IllegalAccessException {
//        List<DemoDTO> demoDTOList = new ArrayList<>();
//        for (SearchHit hit: searchResponse.getHits()
//             ) {
//            Map<String ,Object> map = hit.getSource();
//            DemoDTO demoDTO = new DemoDTO();
//            demoDTO=converterTo(map);
//            demoDTOList.add(demoDTO);
//        }
//        return demoDTOList;
//    }
//    //map  to bean
//    public DemoDTO converterTo(Map<String,Object> map) throws InvocationTargetException, IllegalAccessException {
//        DemoDTO demoDTO = new DemoDTO();
//        org.apache.commons.beanutils.BeanUtils.populate(demoDTO,map);
//        return demoDTO;
//    }
//
//}
