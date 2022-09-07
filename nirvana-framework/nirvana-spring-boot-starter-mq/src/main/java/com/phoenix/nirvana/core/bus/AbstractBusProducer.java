package com.phoenix.nirvana.core.bus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.cloud.bus.event.Destination;
import org.springframework.cloud.bus.event.PathDestinationFactory;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

/**
 * @author xuyongkang
 * @version 1.0
 * @description: 基于 Spring Cloud Bus 实现的 Producer 抽象类
 * @date 2022/7/20 2:37 PM
 */
public abstract class AbstractBusProducer {

    @Resource
    protected ApplicationEventPublisher applicationEventPublisher;

    @Resource
    protected ServiceMatcher serviceMatcher;

    @Resource
    protected PathDestinationFactory pathDestinationFactory;

    @Value("${spring.application.name}")
    protected String applicationName;

    protected void publishEvent(RemoteApplicationEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    /**
     * @return 只广播给自己服务的实例
     */
    protected String selfOriginService() {
        return applicationName + ":**";
    }

    /**
     *
     * @return
     */
    protected Destination selfDestinationService() {
        return pathDestinationFactory.getDestination(selfOriginService());
    }


    protected String getBusId() {
        return serviceMatcher.getBusId();
    }

}
