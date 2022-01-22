package org.designer.cache.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redisson配置
 *
 * @description: Redisson缓存
 * @author: Designer
 * @date : 2021/9/8 13:37
 * @see org.redisson.config.Config;
 */
@Deprecated
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonProperties {

    /**
     * Host
     */
    private String host = "localhost";

    /**
     * 端口号
     */
    private int port = 6379;

    /**
     * 数据库
     */
    private int database = 0;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间
     */
    private int timeout = 3000;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
