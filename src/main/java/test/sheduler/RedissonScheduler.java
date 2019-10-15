package test.sheduler;

import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedissonScheduler {
    @Autowired
    private RedissonClient redissonClient;

    @EventListener({ContextRefreshedEvent.class})
    public void run() {
        RScheduledExecutorService executor = redissonClient.getExecutorService("defaultExecutor");
        executor.scheduleWithFixedDelay(new RadissonRunnableTask("Task " + 3), 0L, 1000L, TimeUnit.MILLISECONDS);
    }
}