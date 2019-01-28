package com.ayt.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
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
        InetSocketTransportAddress inetSocketTransportAddress = new InetSocketTransportAddress(InetAddress.getByName("192.168.0.0"),9300);
        //设置节点配置信息
        Settings settings= Settings.builder().put("","").build();
        //实力化ES客户端对象
        TransportClient client= new PreBuiltTransportClient(settings);
        client.addTransportAddresses();
        return client;
    }

}
