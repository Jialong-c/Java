package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer{

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    public final int getAndIncrement(){
        int current,next;
        do{
            current=this.atomicInteger.get();
            next=(current>=Integer.MAX_VALUE)?0:current+1;
        }while (this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****next:"+next);
        return next;
    }
    @Override
    public ServiceInstance INSTANCE(List<ServiceInstance> serviceInstances) {
        int index=getAndIncrement()%serviceInstances.size();
        return serviceInstances.get(index);
    }
}
