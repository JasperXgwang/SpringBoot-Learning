package com.didispace.web;

import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://www.jianshu.com/p/9417d63b9add
 */
@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping("/hello")
    public String index() throws InterruptedException {
        functionA();
        functionB();
        functionC();
        return "Hello World";
    }

    @Trace
    private void functionA() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(100, 200);
        Thread.sleep(rangeLong);
        LOGGER.info("functionA traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        //在被追踪的方法中自定义 tag.
        ActiveSpan.tag("functionA_tag", "exec functionA");
        functionA_1();
    }

    @Trace
    private void functionA_1() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(100, 200);
        Thread.sleep(rangeLong);
        LOGGER.info("functionA_1 traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        ActiveSpan.tag("functionA_1_tag", "exec functionA_1");

        functionA_1_1();
        functionA_1_2();
    }

    @Trace
    private void functionA_1_1() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(100, 200);
        Thread.sleep(rangeLong);
        LOGGER.info("functionA_1_1 traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        ActiveSpan.tag("functionA_1_1_tag", "exec functionA_1_1");
    }

    @Trace
    private void functionA_1_2() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(100, 200);
        Thread.sleep(rangeLong);
        LOGGER.info("functionA_1_2 traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        ActiveSpan.tag("functionA_1_2_tag", "exec functionA_1_2");
    }

    @Trace
    private void functionB() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(200, 300);
        Thread.sleep(rangeLong);
        LOGGER.info("functionB traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        ActiveSpan.tag("functionB_tag", "exec functionB");
    }

    @Trace
    private void functionC() throws InterruptedException {
        long rangeLong = new RandomDataGenerator().nextLong(10, 200);
        Thread.sleep(rangeLong);
        LOGGER.info("functionC traceId:{} use:{} ms", TraceContext.traceId(), rangeLong);
        ActiveSpan.tag("functionC_tag", "exec functionC");
    }

    @RequestMapping("/press")
    public String press() {
        return "Hello press";
    }
}