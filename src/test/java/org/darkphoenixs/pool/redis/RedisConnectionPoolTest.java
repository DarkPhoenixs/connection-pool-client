package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import org.darkphoenixs.pool.PoolConfig;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisConnectionPoolTest {

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
	public void test() throws Exception {

		try {
			RedisConnectionPool pool = new RedisConnectionPool(
					RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT);
			pool.close();
		} catch (Exception e) {
		}

		try {
			Properties prop = new Properties();
			prop.setProperty(RedisConfig.ADDRESS_PROPERTY,
					RedisConfig.DEFAULT_HOST + ":" + RedisConfig.DEFAULT_PORT);
			prop.setProperty(RedisConfig.CONN_TIMEOUT_PROPERTY,
					RedisConfig.DEFAULT_TIMEOUT + "");
			prop.setProperty(RedisConfig.SO_TIMEOUT_PROPERTY,
					RedisConfig.DEFAULT_TIMEOUT + "");
			prop.setProperty(RedisConfig.DATABASE_PROPERTY,
					RedisConfig.DEFAULT_DATABASE + "");

			RedisConnectionPool pool = new RedisConnectionPool(
					new PoolConfig(), prop);
			pool.close();
		} catch (Exception e) {
		}

		try {
			RedisConnectionPool pool = new RedisConnectionPool(
					new PoolConfig(), RedisConfig.DEFAULT_HOST);
			pool.close();
		} catch (Exception e) {
		}

		RedisConnectionPool pool = new RedisConnectionPool();

		try {
			pool.getConnection();
		} catch (Exception e) {
		}

		try {
			
			Jedis jedis = pool.getConnection();
			
			pool.returnConnection(jedis);
			
			jedis.close();
			
			pool.returnConnection(jedis);

		} catch (Exception e) {
		}

		try {
			pool.invalidateConnection(null);
		} catch (Exception e) {
		}

		pool.close();
	}
}
