package org.darkphoenixs.pool.http;

import org.darkphoenixs.pool.PoolConfig;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class HttpTest {

    @Test
    public void test() throws Exception {

        PoolConfig config = new PoolConfig();
        config.setMaxTotal(20);
        config.setMaxIdle(5);
        config.setMaxWaitMillis(1000);
        config.setTestOnBorrow(true);

        HttpConnectionPool pool = new HttpConnectionPool(config, "https://www.baidu.com/");

        HttpURLConnection connection = pool.getConnection();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String line;

        while ((line = reader.readLine()) != null) {

            System.out.println(line);
        }

        reader.close();

        pool.returnConnection(connection);

        pool.close();
    }
}
