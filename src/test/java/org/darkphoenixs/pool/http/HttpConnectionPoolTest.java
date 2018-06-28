package org.darkphoenixs.pool.http;

import org.darkphoenixs.pool.PoolConfig;
import org.junit.Test;

import java.net.Proxy;
import java.util.Properties;

public class HttpConnectionPoolTest {

    @Test
    public void test() throws Exception {

        PoolConfig config = new PoolConfig();
        config.setMaxTotal(1);
        config.setMaxIdle(1);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);

        Properties properties = new Properties();
        properties.setProperty(HttpConfig.HTTP_URL_PROPERTY, HttpConfig.DEFAULT_URL);
        properties.setProperty(HttpConfig.HTTP_METHOD_PROPERTY, HttpConfig.DEFAULT_METHOD);

        HttpConnectionPool pool = new HttpConnectionPool();

        try {
            pool.getConnection();
        } catch (Exception e) {
        }

        try {
            pool.returnConnection(null);
        } catch (Exception e) {
        }

        try {
            pool.invalidateConnection(null);
        } catch (Exception e) {
        }

        try {
            new HttpConnectionPool(HttpConfig.DEFAULT_URL);
        } catch (Exception e) {
        }
        try {
            new HttpConnectionPool(config, HttpConfig.DEFAULT_URL);
        } catch (Exception e) {
        }

        try {
            new HttpConnectionPool(Proxy.NO_PROXY, HttpConfig.DEFAULT_URL);
        } catch (Exception e) {
        }

        try {
            new HttpConnectionPool(config, Proxy.NO_PROXY, HttpConfig.DEFAULT_URL);
        } catch (Exception e) {
        }

        try {
            new HttpConnectionPool(config, properties);
        } catch (Exception e) {
        }


    }
}