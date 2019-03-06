
import java.util.List;

import org.junit.Test;

import com.chan.hystrix.service.TagQueryCommandA;

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
