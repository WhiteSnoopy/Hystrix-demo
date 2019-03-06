package com.chan.hystrix.service;

import java.util.Collections;
import java.util.List;

import com.chan.hystrix.util.ApplicationContextHelper;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;

/**
 * 信号量隔离
 *
 * @author chanyun
 */
public class TagQueryCommandA extends HystrixCommand<List<String>> {

    private TagService tagService;

    public TagQueryCommandA() {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TagService"))
            .andCommandKey(HystrixCommandKey.Factory.asKey("TagQueryCommand"))
            .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("TagServicePool"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(3)));
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
