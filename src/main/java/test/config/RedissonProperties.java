package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Обёртка для св-в Redis
 */
@ConfigurationProperties(prefix = "spring.redis")
@Component
public class RedissonProperties {
    public static final String DEFAULT_EXECUTOR = "defaultExecutor";
    private Map<String, Integer> executorServiceWorkers = new HashMap<String, Integer>();
    private String host;
    private String port;
    private String password;
    private String clientName;
    private String masterName;
    private int database;
    private boolean clustered;

    private int connectionPoolSize;
    private int connectionMinimumIdleSize;

    private int subscriptionConnectionPoolSize;
    private int subscriptionConnectionMinimumIdleSize;

    public RedissonProperties() {
    }

    public RedissonProperties(Map<String, Integer> executorServiceWorkers, String host, String port, String password, String clientName, String masterName, int database, boolean clustered, int connectionPoolSize, int connectionMinimumIdleSize, int subscriptionConnectionPoolSize, int subscriptionConnectionMinimumIdleSize) {
        this.executorServiceWorkers = executorServiceWorkers;
        this.host = host;
        this.port = port;
        this.password = password;
        this.clientName = clientName;
        this.masterName = masterName;
        this.database = database;
        this.clustered = clustered;
        this.connectionPoolSize = connectionPoolSize;
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
    }

    public static String getDefaultExecutor() {
        return DEFAULT_EXECUTOR;
    }

    public Map<String, Integer> getExecutorServiceWorkers() {
        return executorServiceWorkers;
    }

    public void setExecutorServiceWorkers(Map<String, Integer> executorServiceWorkers) {
        this.executorServiceWorkers = executorServiceWorkers;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public boolean isClustered() {
        return clustered;
    }

    public void setClustered(boolean clustered) {
        this.clustered = clustered;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }

    public int getSubscriptionConnectionPoolSize() {
        return subscriptionConnectionPoolSize;
    }

    public void setSubscriptionConnectionPoolSize(int subscriptionConnectionPoolSize) {
        this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
    }

    public int getSubscriptionConnectionMinimumIdleSize() {
        return subscriptionConnectionMinimumIdleSize;
    }

    public void setSubscriptionConnectionMinimumIdleSize(int subscriptionConnectionMinimumIdleSize) {
        this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
    }
}
