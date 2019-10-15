package test.config;

import org.redisson.Redisson;
import org.redisson.api.RLiveObjectService;
import org.redisson.api.RedissonClient;
import org.redisson.config.RedissonNodeConfig;
import org.redisson.config.SubscriptionMode;
import org.redisson.spring.transaction.RedissonTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;

@Configuration
public class RedissonConfiguration {
    @Bean("redissonConfig")
    public RedissonNodeConfig redissonConfig(RedissonProperties redissonProperties) {
        RedissonNodeConfig config = new RedissonNodeConfig();

        String address = String.format("redis://%s:%s", redissonProperties.getHost(), redissonProperties.getPort());
        if (redissonProperties.isClustered()) {
            config.useSentinelServers()
                    .setSubscriptionMode(SubscriptionMode.SLAVE)
                    .setMasterName(redissonProperties.getMasterName())
                    .addSentinelAddress(address)
                    .setPassword(redissonProperties.getPassword())
                    .setClientName(redissonProperties.getClientName())
                    .setDatabase(redissonProperties.getDatabase())
                    .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                    .setMasterConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                    .setSubscriptionConnectionPoolSize(redissonProperties.getSubscriptionConnectionPoolSize())
                    .setSubscriptionConnectionMinimumIdleSize(redissonProperties.getSubscriptionConnectionMinimumIdleSize());
        } else {
            config.useSingleServer().setAddress(address)
                    .setPassword(redissonProperties.getPassword())
                    .setClientName(redissonProperties.getClientName())
                    .setDatabase(redissonProperties.getDatabase())
                    .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                    .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                    .setSubscriptionConnectionPoolSize(redissonProperties.getSubscriptionConnectionPoolSize())
                    .setSubscriptionConnectionMinimumIdleSize(redissonProperties.getSubscriptionConnectionMinimumIdleSize());
        }
        // default properties
        redissonProperties.getExecutorServiceWorkers().putIfAbsent(RedissonProperties.DEFAULT_EXECUTOR, 10);
        config.setExecutorServiceWorkers(redissonProperties.getExecutorServiceWorkers());
        return config;
    }

    @Primary
    @Bean("redissonClient")
    public RedissonClient redissonClient(RedissonNodeConfig redissonConfig) {
        RedissonClient redisson = Redisson.create(redissonConfig);

        for (Map.Entry<String, Integer> entry : redissonConfig.getExecutorServiceWorkers().entrySet()) {
            String name = entry.getKey();
            int workers = entry.getValue();
            redisson.getExecutorService(name).registerWorkers(workers);
        }

        return redisson;
    }

    @Bean("liveObjectService")
    public RLiveObjectService liveObjectService(RedissonClient redissonClient) {
        RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
        return liveObjectService;
    }

    @Bean
    public RedissonTransactionManager transactionManager(RedissonClient redissonClient) {
        return new RedissonTransactionManager(redissonClient);
    }
}
