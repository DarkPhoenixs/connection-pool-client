package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisConnectionFactoryTest2 {

	@Test
	public void test_0() throws Exception {

		RedisConnectionFactory factory = new RedisConnectionFactory(
				RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
				RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
				RedisConfig.DEFAULT_PASSWORD, 1, RedisConfig.DEFAULT_CLIENTNAME);

		try {
			factory.activateObject(new DefaultPooledObject<Jedis>(
					new JedisConn(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<Jedis>(new JedisConn(
					RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.activateObject(new DefaultPooledObject<Jedis>(
					new JedisConn2(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn2(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<Jedis>(
					new JedisConn2(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn3("127.0.0.2", RedisConfig.DEFAULT_PORT)));

		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn3(RedisConfig.DEFAULT_HOST, 1233)));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn3(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(
					new JedisConn4(RedisConfig.DEFAULT_HOST,
							RedisConfig.DEFAULT_PORT)));
		} catch (Exception e) {
		}
	}

	@Test
	public void test_1() throws Exception {

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
