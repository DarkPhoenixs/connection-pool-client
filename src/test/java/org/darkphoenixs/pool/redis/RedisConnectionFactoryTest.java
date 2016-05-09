package org.darkphoenixs.pool.redis;

import java.util.Properties;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisConnectionFactoryTest {

	@Test
	public void test() throws Exception {

		Properties prop = new Properties();
		
		RedisConnectionFactory factory;
		
		try {
			factory = new RedisConnectionFactory(prop);

		} catch (Exception e) {
		}

		prop.setProperty(RedisConfig.ADDRESS_PROPERTY, RedisConfig.DEFAULT_HOST
				+ ":" + RedisConfig.DEFAULT_PORT);
		prop.setProperty(RedisConfig.CONN_TIMEOUT_PROPERTY,
				RedisConfig.DEFAULT_TIMEOUT + "");
		prop.setProperty(RedisConfig.SO_TIMEOUT_PROPERTY,
				RedisConfig.DEFAULT_TIMEOUT + "");
		prop.setProperty(RedisConfig.DATABASE_PROPERTY,
				RedisConfig.DEFAULT_DATABASE + "");
		prop.setProperty(RedisConfig.CLIENTNAME_PROPERTY, "Test");
		prop.setProperty(RedisConfig.PASSWORD_PROPERTY, "Test");

		factory = new RedisConnectionFactory(prop);

		factory = new RedisConnectionFactory(RedisConfig.DEFAULT_HOST,
				RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT,
				RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD,
				RedisConfig.DEFAULT_DATABASE, RedisConfig.DEFAULT_CLIENTNAME);

		try {
			factory.makeObject();

		} catch (Exception e) {
		}

		factory.activateObject(new DefaultPooledObject<Jedis>(new Jedis()));

		try {
			factory.activateObject(new DefaultPooledObject<Jedis>(null));
		} catch (Exception e) {
		}

		factory.validateObject(new DefaultPooledObject<Jedis>(new Jedis()));

		try {
			factory.validateObject(new DefaultPooledObject<Jedis>(null));
		} catch (Exception e) {
		}

		factory.passivateObject(new DefaultPooledObject<Jedis>(new Jedis()));

		try {
			factory.passivateObject(new DefaultPooledObject<Jedis>(null));
		} catch (Exception e) {
		}

		factory.destroyObject(new DefaultPooledObject<Jedis>(new Jedis()));

		try {
			factory.destroyObject(new DefaultPooledObject<Jedis>(null));
		} catch (Exception e) {
		}
	}
}
