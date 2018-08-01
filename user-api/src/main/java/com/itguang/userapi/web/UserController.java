package com.itguang.userapi.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户微服务
 *
 * @author itguang
 * @create 2017-12-16 9:43
 **/
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DiscoveryClient client;

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

        ServiceInstance instance = client.getLocalServiceInstance();
        log.info("host={},service_id={},port={}", instance.getHost(), instance.getServiceId(), instance.getPort());
        //日志打印：2018-08-01 14:32:01.063  INFO 26388 --- [nio-8001-exec-1] com.itguang.userapi.web.UserController   : host=LAPTOP-Q751V0F2,service_id=hello-service,port=8001
        return "hello world from user_api1";
    }

    /**
     * 获得服务提供者的服务名
     * @return
     */
    @RequestMapping(value="/client", method = RequestMethod.GET)
    public String getServiceName(){
        String service = client.getServices().toString();
        return "service name : " + service +  " & port :" +  port;

        //service：[ribbon-consumer, hello-service, eureka-service]
    }
}
