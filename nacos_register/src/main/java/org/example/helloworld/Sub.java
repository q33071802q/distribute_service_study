package org.example.helloworld;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

public class Sub {
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serviceName = "helloworld.services";
        NamingService naming = NamingFactory.createNamingService("127.0.0.1:8848");
        naming.subscribe(serviceName,event->{
            if (event instanceof NamingEvent){
                System.out.println("订阅到数据");
                System.out.println(((NamingEvent) event).getInstances());
            }
        });
        System.out.println("订阅完成");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
