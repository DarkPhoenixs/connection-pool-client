package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.PoolConfig;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Hashing;

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
	public void test_0() throws Exception {

		JedisShardInfo info = new JedisShardInfo(RedisConfig.DEFAULT_HOST,
				RedisConfig.DEFAULT_PORT);

		RedisShardedConnPool pool = new RedisShardedConnPool(new PoolConfig(),
				Arrays.asList(new JedisShardInfo[] { info }));

		pool.close();

		RedisShardedConnPool.RedisShardedConnFactory factory = pool.new RedisShardedConnFactory(
				Arrays.asList(new JedisShardInfo[] { info }),
				Hashing.MURMUR_HASH, null);

		factory.activateObject(factory.makeObject());

		factory.validateObject(factory.makeObject());

		factory.passivateObject(factory.makeObject());

		factory.destroyObject(factory.makeObject());

		try {
			factory.activateObject(null);
		} catch (Exception e) {
		}

		try {
			factory.validateObject(null);
		} catch (Exception e) {
		}

		try {
			factory.passivateObject(null);
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(null);
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedis(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn2(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn3(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn4(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn2(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn3(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<ShardedJedis>(
					new ShardedJedisConn4(Arrays
							.asList(new JedisShardInfo[] { info }))));
		} catch (Exception e) {
		}
	}

	@Test
	public void test_1() throws Exception {

		JedisShardInfo info = new JedisShardInfo(RedisConfig.DEFAULT_HOST,
				RedisConfig.DEFAULT_PORT);

		RedisShardedConnPool pool = new RedisShardedConnPool(new PoolConfig(),
				Arrays.asList(new JedisShardInfo[] { info }),
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

		shardedJedis.close();
		
		try {
			pool.returnConnection(shardedJedis);
		} catch (Exception e) {
		}

		try {
			pool.returnConnection(new ShardedJedisConn5(Arrays
					.asList(new JedisShardInfo[] { info })));
		} catch (Exception e) {
		}

		try {
			pool.invalidateConnection(null);
		} catch (Exception e) {
		}

		pool.close();
	}
}
