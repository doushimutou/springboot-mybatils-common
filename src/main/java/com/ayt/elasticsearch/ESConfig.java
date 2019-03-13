package com.ayt.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description
 * Author ayt  on
 */
@Configuration
public class ESConfig {

    @Bean
    public TransportClient client() throws UnknownHostException {

        //设置ES的host地址
        TransportAddress transportAddress = new TransportAddress(InetAddress.getByName("118.25.127.220"),9300);
        //设置节点配置信息
        Settings settings= Settings.builder().put("cluster.name","my-application").build();
        //实力化ES客户端对象
        TransportClient client= new PreBuiltTransportClient(settings);
        client.addTransportAddresses(transportAddress);
        return client;
    }

}
