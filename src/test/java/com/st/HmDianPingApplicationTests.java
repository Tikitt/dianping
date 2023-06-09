package com.st;

import com.st.service.impl.ShopServiceImpl;
import com.st.utils.RedisIdWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
@RunWith(SpringRunner.class)
public class HmDianPingApplicationTests {
   @Resource
    private ShopServiceImpl shopService;

    @Test
    public void testSaveShop() throws InterruptedException {
        shopService.saveShop2Redis(1L,10L);
    }
    @Resource
    private RedisIdWorker redisIdWorker;
    private ExecutorService es = Executors.newFixedThreadPool(500);// 线程池
    @Test
    public void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);// 信号枪：保证分线程运行完后，主线程在运行【主要的作用是同步协调在多线程的等待与唤醒问题】
        // 实现Runnable接口的类
        Runnable task = () -> {
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();// 执行完一个分线程就减少一个变量
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);// 将某个任务添加到线程池，线程池会为每个任务创建一个线程，该线程会在之后的某个时刻自动执行。
        }
        latch.await();// 阻塞主线程
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }

}
