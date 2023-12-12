package org.example.helloworld;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;

public class Pub {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serviceName = "helloworld.services";
        NamingService naming = NamingFactory.createNamingService("127.0.0.1:8848");
        naming.registerInstance(serviceName,"127.0.0.1",8888);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
