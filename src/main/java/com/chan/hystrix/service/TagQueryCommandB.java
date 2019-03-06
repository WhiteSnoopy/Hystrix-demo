package com.chan.hystrix.service;

import java.util.Collections;
import java.util.List;

import com.chan.hystrix.util.ApplicationContextHelper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 线程隔离
 *
 * @author chanyun
 */
public class TagQueryCommandB extends HystrixCommand<List<String>> {

    private TagService tagService;

    public TagQueryCommandB() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TagService"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("TagQueryCommand"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("TagServicePool"))
            .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(200))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                .withCircuitBreakerEnabled(true)
                .withCircuitBreakerRequestVolumeThreshold(3)
                .withCircuitBreakerErrorThresholdPercentage(80)
                .withExecutionTimeoutInMilliseconds(10000)));
        this.tagService = ApplicationContextHelper.getBean(TagService.class);
    }

    @Override
    protected List<String> run() {
        return tagService.queryTag();
    }

    @Override
    protected List<String> getFallback() {
        return Collections.EMPTY_LIST;
    }
}
