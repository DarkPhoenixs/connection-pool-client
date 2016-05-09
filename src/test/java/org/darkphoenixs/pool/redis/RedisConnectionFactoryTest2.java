package org.darkphoenixs.pool.redis;

import org.junit.Test;

public class RedisConnectionFactoryTest2 {

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
