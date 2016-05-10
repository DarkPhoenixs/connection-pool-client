package org.darkphoenixs.pool.redis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.darkphoenixs.pool.PoolConfig;
import org.darkphoenixs.pool.redis.RedisSentinelConnPool.RedisMasterListener;
import org.darkphoenixs.pool.redis.RedisSentinelConnPool.RedisMasterPubSub;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

public class RedisSentinelConnPoolTest {

	@Before
	public void before() throws Exception {

		Thread th = new Thread(new Runnable() {

			private ServerSocket serverSocket;

			@Override
			public void run() {

				try {
					serverSocket = new ServerSocket(6379);

					serverSocket.accept();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		th.setDaemon(true);
		th.start();

		Thread th2 = new Thread(new Runnable() {

			private ServerSocket serverSocket;

			@Override
			public void run() {

				try {
					serverSocket = new ServerSocket(26379);

					serverSocket.accept();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		th2.setDaemon(true);
		th2.start();
	}

	@Test
	public void test_0() throws Exception {

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool("localhost",
					new HashSet<String>(
							Arrays.asList(new String[] { "localhost:6379" })));
			pool.close();
		} catch (Exception e) {

		}

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool("localhost",
					new HashSet<String>(Arrays
							.asList(new String[] { "localhost:6379" })),
					"test");
			pool.close();
		} catch (Exception e) {
		}

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool("localhost",
					new HashSet<String>(Arrays
							.asList(new String[] { "localhost:6379" })),
					new PoolConfig(), 2000, 2000, "test", 0);
			pool.close();
		} catch (Exception e) {
		}

		Properties properties = new Properties();

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool(
					new PoolConfig(), properties);
			pool.close();
		} catch (Exception e) {
		}

		properties.setProperty(RedisConfig.MASTERNAME_PROPERTY, "mymaster");

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool(
					new PoolConfig(), properties);
			pool.close();
		} catch (Exception e) {
		}

		properties
				.setProperty(RedisConfig.SENTINELS_PROPERTY, "localhost:6379");

		properties.setProperty(RedisConfig.CONN_TIMEOUT_PROPERTY,
				RedisConfig.DEFAULT_TIMEOUT + "");
		properties.setProperty(RedisConfig.SO_TIMEOUT_PROPERTY,
				RedisConfig.DEFAULT_TIMEOUT + "");
		properties.setProperty(RedisConfig.DATABASE_PROPERTY,
				RedisConfig.DEFAULT_DATABASE + "");
		properties.setProperty(RedisConfig.CLIENTNAME_PROPERTY, "Test");
		properties.setProperty(RedisConfig.PASSWORD_PROPERTY, "Test");

		try {
			RedisSentinelConnPool pool = new RedisSentinelConnPool(
					new PoolConfig(), properties);

			pool.close();
		} catch (Exception e) {
		}
	}

	@Test
	public void test_1() throws Exception {

		final RedisSentinelConnPoolDemo pool = new RedisSentinelConnPoolDemo();

		try {
			pool.poolConfig = new PoolConfig();

			pool.initPool(pool.toHostAndPort(Arrays.asList(new String[] {
					"localhost", "6379" })));

			pool.getCurrentHostMaster();

		} catch (Exception e) {
		}

		try {
			pool.returnConnection(pool.getConnection());
		} catch (Exception e) {
		}

		try {
			pool.initPool(pool.toHostAndPort(Arrays.asList(new String[] {
					"localhost", "26379" })));

			pool.initPool(new PoolConfig(), new RedisConnectionFactory(
					"localhost", 6379, RedisConfig.DEFAULT_TIMEOUT,
					RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD,
					RedisConfig.DEFAULT_DATABASE,
					RedisConfig.DEFAULT_CLIENTNAME));

		} catch (Exception e) {
		}

		try {
			Thread thr = new Thread(new Runnable() {

				@Override
				public void run() {

					pool.returnConnection(pool.getResource());
				}
			});

			thr.setDaemon(true);
			thr.start();

		} catch (Exception e) {
		}

		Thread.sleep(3000);

		try {
			pool.initPool(pool.toHostAndPort(Arrays.asList(new String[] {
					"localhost", "6379" })));

			pool.returnConnection(pool.getConnection());
		} catch (Exception e) {
		}

		try {
			pool.returnConnection(new Jedis() {
				@Override
				public Client getClient() {

					return new Client() {

						@Override
						public boolean isBroken() {

							return true;
						}
					};
				}

			});

		} catch (Exception e) {
		}

		try {
			pool.invalidateConnection(null);
		} catch (Exception e) {
		}

		try {
			pool.destroy();
		} catch (Exception e) {
		}

		try {
			pool.close();
		} catch (Exception e) {

		}

	}

	@Test
	public void test_2() throws Exception {

		RedisSentinelConnPool pool = new RedisSentinelConnPool();

		pool.poolConfig = new PoolConfig();

		pool.new RedisMasterPubSub();

		RedisMasterPubSub pubSub = pool.new RedisMasterPubSub("localhost",
				"localhost", 6379);

		pubSub.onMessage("channel", "message");

		pubSub.onMessage("channel", "localhost1 x x localhost 6379");
		
		pubSub.onMessage("channel", "localhost x x localhost 6379");

		try {
			pool.close();
		} catch (Exception e) {
		}
	}

	@Test
	public void test_3() throws Exception {
		
		RedisSentinelConnPool pool = new RedisSentinelConnPool();

		pool.poolConfig = new PoolConfig();

		RedisMasterListener lis1 = pool.new RedisMasterListener("localhost",
				"localhost", 6379);
		lis1.setDaemon(true);
		lis1.start();
		RedisMasterListener lis2 = pool.new RedisMasterListener("localhost",
				"localhost", 6379, 5000);
		lis2.setDaemon(true);
		lis2.start();
		
		try {
			Thread.sleep(2000);
			
			pool.close();
		} catch (Exception e) {
		}
		
		try {
			lis1.shutdown();
			lis2.shutdown();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private static class RedisSentinelConnPoolDemo extends
			RedisSentinelConnPool {

		/** serialVersionUID */
		private static final long serialVersionUID = 1L;

		public RedisSentinelConnPoolDemo() {

			masterListeners.add(new RedisMasterListener());
		}

		@Override
		protected void initPool(GenericObjectPoolConfig poolConfig,
				PooledObjectFactory<Jedis> factory) {
			// TODO Auto-generated method stub
			super.initPool(poolConfig, factory);
		}
	}
}
