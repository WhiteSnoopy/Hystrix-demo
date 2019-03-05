
/**
 * @(#)HystrixTest.java, 2018/12/19.
 * <p/>
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.util.List;

import org.junit.Test;

import com.netease.mail.hystrix.service.TagQueryCommandA;

/**
 * @author chanyun
 */
public class HystrixTest extends TestBase {

    @Test
    public void test() {
        /**
         * 降级触发类型
         * 1. run方法抛出非HystrixBadRequestException
         * 2. run方法运行超时
         * 3. 触发熔断策略
         * 4. 线程池/信号量已满
         */
        List<String> result = new TagQueryCommandA().execute();
        System.out.println(result);
    }
}
