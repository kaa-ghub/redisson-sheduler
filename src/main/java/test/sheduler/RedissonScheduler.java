package ru.bcs.bank.ms.courses.support.scheduler;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RScheduledExecutorService;
import org.redisson.api.RedissonClient;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.bcs.bank.ms.courses.config.SchedulingConfiguration;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedissonScheduler {
    private final RedissonClient redissonClient;
    private final SchedulingConfiguration schedulingConfiguration;
    private final SpELCommandRunner spELCommandRunner;

    @EventListener({ContextRefreshedEvent.class})
    public void run() {
//        if (isNotActiveTasks("ETExecutor")) {
//            RScheduledExecutorService executor = redissonClient.getExecutorService("ETExecutor");
//            executor.scheduleWithFixedDelay(new RadissonRunnableTask(spELCommandRunner, "@schedulingServiceImpl.updateEtClientsAndInstruments()"), 0L, schedulingConfiguration.getUpdateEt(), TimeUnit.MILLISECONDS);
//        }
//
//        if (isNotActiveTasks("CBRExecutor")) {
//            RScheduledExecutorService executor = redissonClient.getExecutorService("CBRExecutor");
//            executor.scheduleWithFixedDelay(new RadissonRunnableTask(spELCommandRunner, "@schedulingServiceImpl.updateCbrCourses()"), 0L, schedulingConfiguration.getUpdateCbr(), TimeUnit.MILLISECONDS);
//        }
//
//        if (isNotActiveTasks("MailExecutor")) {
//            RScheduledExecutorService executor = redissonClient.getExecutorService("MailExecutor");
//            executor.scheduleWithFixedDelay(new RadissonRunnableTask(spELCommandRunner, "@schedulingServiceImpl.checkMailSending()"), 0L, schedulingConfiguration.getCheckMail(), TimeUnit.MILLISECONDS);
//        }
    }

    private boolean isNotActiveTasks(String executorName) {
        return !isActiveTasks(executorName);
    }

    private boolean isActiveTasks(String executorName) {
        return redissonClient.getAtomicLong("{"+ executorName +":org.redisson.executor.RemoteExecutorService}:counter").isExists()
                && redissonClient.getAtomicLong("{"+ executorName +":org.redisson.executor.RemoteExecutorService}:counter").get() > 0;
    }
}
