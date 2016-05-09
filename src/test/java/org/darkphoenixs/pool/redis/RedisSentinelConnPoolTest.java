package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

import org.darkphoenixs.pool.PoolConfig;
import org.junit.Before;
import org.junit.Test;

public class RedisSentinelConnPoolTest {

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
		
		Properties properties = new Properties();
		
//		properties.setProperty(RedisConfig.MASTERNAME_PROPERTY, "");
//		properties.setProperty(RedisConfig.CONN_TIMEOUT_PROPERTY,
//				RedisConfig.DEFAULT_TIMEOUT + "");
//		properties.setProperty(RedisConfig.SO_TIMEOUT_PROPERTY,
//				RedisConfig.DEFAULT_TIMEOUT + "");
//		properties.setProperty(RedisConfig.DATABASE_PROPERTY,
//				RedisConfig.DEFAULT_DATABASE + "");
//		properties.setProperty(RedisConfig.CLIENTNAME_PROPERTY, "Test");
//		properties.setProperty(RedisConfig.PASSWORD_PROPERTY, "Test");

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool(new PoolConfig(), properties);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
