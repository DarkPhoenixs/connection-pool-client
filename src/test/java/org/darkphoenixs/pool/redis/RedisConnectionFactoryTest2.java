package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;

import org.junit.Test;

public class RedisConnectionFactoryTest2 {

	static {

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
	public void test_0() throws Exception {

		RedisConnectionFactory factory = new RedisConnectionFactory(
				RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
				RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
				"test", RedisConfig.DEFAULT_DATABASE,
				RedisConfig.DEFAULT_CLIENTNAME);
		try {
			factory.makeObject();
		} catch (Exception e) {
		}

		factory = new RedisConnectionFactory(RedisConfig.DEFAULT_HOST,
				RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT,
				RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD, 3,
				RedisConfig.DEFAULT_CLIENTNAME);
		try {
			factory.makeObject();
		} catch (Exception e) {
		}

		factory = new RedisConnectionFactory(RedisConfig.DEFAULT_HOST,
				RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT,
				RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD,
				RedisConfig.DEFAULT_DATABASE, "test");
		try {
			factory.makeObject();
		} catch (Exception e) {
		}
	}
}
