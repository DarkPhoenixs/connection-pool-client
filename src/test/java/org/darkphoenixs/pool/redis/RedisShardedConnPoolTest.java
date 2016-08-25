package org.darkphoenixs.pool.redis;

import org.darkphoenixs.pool.PoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.regex.Pattern;

public class RedisShardedConnPoolTest {

    @Before
    public void before() throws Exception {

        Thread th = new Thread(new Runnable() {

            private ServerSocket serverSocket;

            @Override
            public void run() {

                try {
                    serverSocket = new ServerSocket(RedisConfig.DEFAULT_PORT);

                    serverSocket.accept();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        th.setDaemon(true);
        th.start();
    }

    @Test
    public void test_1() throws Exception {

        JedisShardInfo info1 = new JedisShardInfo(RedisConfig.DEFAULT_HOST,
                RedisConfig.DEFAULT_PORT);

        RedisShardedConnPool pool1 = new RedisShardedConnPool(new PoolConfig(),
                Arrays.asList(new JedisShardInfo[]{info1}));
        pool1.close();

        JedisShardInfo info = new JedisShardInfo(RedisConfig.DEFAULT_HOST,
                RedisConfig.DEFAULT_PORT);

        RedisShardedConnPool pool = new RedisShardedConnPool(new PoolConfig(),
                Arrays.asList(new JedisShardInfo[]{info}),
                Pattern.compile(""));

        try {
            pool.getConnection();
        } catch (Exception e) {
        }

        ShardedJedis shardedJedis = pool.getConnection();

        try {
            pool.returnConnection(shardedJedis);
        } catch (Exception e) {
        }

        try {
            shardedJedis.close();
        } catch  (Exception e) {
        }

        try {
            pool.returnConnection(shardedJedis);
        } catch (Exception e) {
        }

        try {
            pool.returnConnection(new ShardedJedisConn5(Arrays
                    .asList(new JedisShardInfo[]{info})));
        } catch (Exception e) {
        }

        try {
            pool.invalidateConnection(null);
        } catch (Exception e) {
        }

        pool.close();
    }
}
