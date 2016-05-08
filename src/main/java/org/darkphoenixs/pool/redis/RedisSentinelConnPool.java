/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.darkphoenixs.pool.redis;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.darkphoenixs.pool.ConnectionException;
import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

import redis.clients.jedis.Client;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * <p>Title: RedisSentinelConnPool</p>
 * <p>Description: Redis哨兵连接池</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see PoolBase
 * @see ConnectionPool
 * @version 1.0
 */
public class RedisSentinelConnPool extends PoolBase<Jedis> implements
		ConnectionPool<Jedis> {

	/** serialVersionUID */
	private static final long serialVersionUID = -2917559115197092907L;

	/** poolConfig */
	protected PoolConfig poolConfig;

	/** connectionTimeout */
	protected int connectionTimeout = RedisConfig.DEFAULT_TIMEOUT;

	/** soTimeout */
	protected int soTimeout = RedisConfig.DEFAULT_TIMEOUT;

	/** password */
	protected String password = RedisConfig.DEFAULT_PASSWORD;

	/** database */
	protected int database = RedisConfig.DEFAULT_DATABASE;

	/** clientName */
	protected String clientName = RedisConfig.DEFAULT_CLIENTNAME;

	/** masterListeners */
	protected Set<RedisMasterListener> masterListeners = new HashSet<RedisMasterListener>();

	/** log */
	protected Logger log = Logger.getLogger(getClass().getName());

	/** factory */
	private volatile RedisConnectionFactory factory;

	/** currentHostMaster */
	private volatile HostAndPort currentHostMaster;

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig) {
		this(masterName, sentinels, poolConfig, RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels) {
		this(masterName, sentinels, new PoolConfig(), RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param password 密码
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			String password) {
		this(masterName, sentinels, new PoolConfig(), RedisConfig.DEFAULT_TIMEOUT, password);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param timeout 超时
	 * @param password 密码
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, int timeout, final String password) {
		this(masterName, sentinels, poolConfig, timeout, password, RedisConfig.DEFAULT_DATABASE);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param timeout 超时
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, final int timeout) {
		this(masterName, sentinels, poolConfig, timeout, RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param password 密码
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, final String password) {
		this(masterName, sentinels, poolConfig, RedisConfig.DEFAULT_TIMEOUT, password);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param timeout 超时
	 * @param password 密码
	 * @param database 数据库
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, int timeout, final String password,
			final int database) {
		this(masterName, sentinels, poolConfig, timeout, timeout, password,
				database);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param timeout 超时
	 * @param password 密码
	 * @param database 数据库
	 * @param clientName 客户端名称
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, int timeout, final String password,
			final int database, final String clientName) {
		this(masterName, sentinels, poolConfig, timeout, timeout, password,
				database, clientName);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param timeout 连接超时
	 * @param soTimeout 超时时间
	 * @param password 密码
	 * @param database 数据库
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, final int timeout,
			final int soTimeout, final String password, final int database) {
		this(masterName, sentinels, poolConfig, timeout, soTimeout, password,
				database, RedisConfig.DEFAULT_CLIENTNAME);
	}

	/**
	 * <p>Title: RedisSentinelConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param masterName 主机名称
	 * @param sentinels 哨兵列表
	 * @param poolConfig 池配置
	 * @param connectionTimeout 连接超时
	 * @param soTimeout 超时时间
	 * @param password 密码
	 * @param database 数据库
	 * @param clientName 客户端名称
	 */
	public RedisSentinelConnPool(String masterName, Set<String> sentinels,
			final PoolConfig poolConfig, final int connectionTimeout,
			final int soTimeout, final String password, final int database,
			final String clientName) {
		this.poolConfig = poolConfig;
		this.connectionTimeout = connectionTimeout;
		this.soTimeout = soTimeout;
		this.password = password;
		this.database = database;
		this.clientName = clientName;

		HostAndPort master = initSentinels(sentinels, masterName);
		initPool(master);
	}
	
	/**
	 * @since 1.2.1
	 * @param poolConfig 池配置
	 * @param properties 参数配置
	 */
	public RedisSentinelConnPool(final PoolConfig poolConfig, final Properties properties) {
		
		this.poolConfig = poolConfig;
		
		if (null != properties.getProperty(RedisConfig.CONN_TIMEOUT_PROPERTY))
			this.connectionTimeout = Integer.parseInt(properties.getProperty(RedisConfig.CONN_TIMEOUT_PROPERTY));
		if (null != properties.getProperty(RedisConfig.SO_TIMEOUT_PROPERTY))
			this.soTimeout = Integer.parseInt(properties.getProperty(RedisConfig.SO_TIMEOUT_PROPERTY));
		if (null != properties.getProperty(RedisConfig.DATABASE_PROPERTY))
			this.database = Integer.parseInt(properties.getProperty(RedisConfig.DATABASE_PROPERTY));
	
		this.password = properties.getProperty(RedisConfig.PASSWORD_PROPERTY);
		this.clientName = properties.getProperty(RedisConfig.CLIENTNAME_PROPERTY);

		String masterName = properties.getProperty(RedisConfig.MASTERNAME_PROPERTY);
		if (masterName == null)
			throw new ConnectionException("[" + RedisConfig.MASTERNAME_PROPERTY + "] is required !");
		
		String sentinels = properties.getProperty(RedisConfig.SENTINELS_PROPERTY);
		if (sentinels == null)
			throw new ConnectionException("[" + RedisConfig.SENTINELS_PROPERTY + "] is required !");
		
		HostAndPort master = initSentinels(new HashSet<String>(Arrays.asList(sentinels.split(","))), masterName);
		
		initPool(master);
	}

	/**
	 * <p>Title: getCurrentHostMaster</p>
	 * <p>Description: 获得当前主机</p>
	 *
	 * @return 当前主机
	 */
	public HostAndPort getCurrentHostMaster() {
		return currentHostMaster;
	}

	/**
	 * <p>Title: initPool</p>
	 * <p>Description: 初始化连接池</p>
	 *
	 * @param master 主机
	 */
	private void initPool(HostAndPort master) {
		if (!master.equals(currentHostMaster)) {
			currentHostMaster = master;
			if (factory == null) {
				factory = new RedisConnectionFactory(master.getHost(),
						master.getPort(), connectionTimeout, soTimeout,
						password, database, clientName);
				initPool(poolConfig, factory);
			} else {
				factory.setHostAndPort(currentHostMaster);
				// although we clear the pool, we still have to check the
				// returned object
				// in getResource, this call only clears idle instances, not
				// borrowed instances
				internalPool.clear();
			}

			log.info("Created JedisPool to master at " + master);
		}
	}

	/**
	 * <p>Title: initSentinels</p>
	 * <p>Description: 初始化哨兵列表</p>
	 *
	 * @param sentinels 哨兵列表
	 * @param masterName 主机名称
	 * @return 主机
	 */
	private HostAndPort initSentinels(Set<String> sentinels,
			final String masterName) {

		HostAndPort master = null;
		boolean sentinelAvailable = false;

		log.info("Trying to find master from available Sentinels...");

		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));

			log.fine("Connecting to Sentinel " + hap);

			Jedis jedis = null;
			try {
				jedis = new Jedis(hap.getHost(), hap.getPort());

				List<String> masterAddr = jedis
						.sentinelGetMasterAddrByName(masterName);

				// connected to sentinel...
				sentinelAvailable = true;

				if (masterAddr == null || masterAddr.size() != 2) {
					log.warning("Can not get master addr, master name: "
							+ masterName + ". Sentinel: " + hap + ".");
					continue;
				}

				master = toHostAndPort(masterAddr);
				log.fine("Found Redis master at " + master);
				break;
			} catch (Exception e) {
				log.warning("Cannot get master address from sentinel running @ " + hap 
						+ ". Reason: " + e + ". Trying next one.");
			} finally {
				if (jedis != null) {
					jedis.close();
				}
			}
		}

		if (master == null) {
			if (sentinelAvailable) {
				// can connect to sentinel, but master name seems to not
				// monitored
				throw new ConnectionException("Can connect to sentinel, but "
						+ masterName + " seems to be not monitored...");
			} else {
				throw new ConnectionException(
						"All sentinels down, cannot determine where is "
								+ masterName + " master is running...");
			}
		}

		log.info("Redis master running at " + master
				+ ", starting Sentinel listeners...");

		for (String sentinel : sentinels) {
			final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel
					.split(":")));
			RedisMasterListener masterListener = new RedisMasterListener(
					masterName, hap.getHost(), hap.getPort());
			// whether MasterListener threads are alive or not, process can be
			// stopped
			masterListener.setDaemon(true);
			masterListeners.add(masterListener);
			masterListener.start();
		}

		return master;
	}

	/**
	 * <p>Title: toHostAndPort</p>
	 * <p>Description: 主机地址转换</p>
	 *
	 * @param getMasterAddrByNameResult 主机地址
	 * @return 主机
	 */
	private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
		String host = getMasterAddrByNameResult.get(0);
		int port = Integer.parseInt(getMasterAddrByNameResult.get(1));

		return new HostAndPort(host, port);
	}

	@Override
	public Jedis getResource() {
		while (true) {
			Jedis jedis = super.getResource();
			
			// get a reference because it can change concurrently
			final HostAndPort master = currentHostMaster;
			final HostAndPort connection = new HostAndPort(jedis.getClient()
					.getHost(), jedis.getClient().getPort());

			if (master.equals(connection)) {
				// connected to the correct master
				return jedis;
			} else {
				invalidateResource(jedis);
			}
		}
	}

	@Override
	public void destroy() {
		for (RedisMasterListener m : masterListeners) {
			m.shutdown();
		}

		super.destroy();
	}
	
	/**
	 * <p>Title: RedisMasterListener</p>
	 * <p>Description: Redis主机监听器</p>
	 *
	 * @since 2015年9月19日
	 * @author Victor
	 * @see Thread
	 * @version 1.0
	 */
	protected class RedisMasterListener extends Thread {

		/** masterName */
		protected String masterName;
		/** host */
		protected String host;
		/** port */
		protected int port;
		/** subscribeRetryWaitTimeMillis */
		protected long subscribeRetryWaitTimeMillis = 5000;
		/** jedis */
		protected volatile Jedis j;
		/** running */
		protected AtomicBoolean running = new AtomicBoolean(false);

		/**
		 * <p>Title: RedisMasterListener</p>
		 * <p>Description: 默认构造方法</p>
		 *
		 */
		protected RedisMasterListener() {}

		/**
		 * <p>Title: RedisMasterListener</p>
		 * <p>Description: 构造方法</p>
		 *
		 * @param masterName 主机名称
		 * @param host 地址
		 * @param port 端口
		 */
		public RedisMasterListener(String masterName, String host, int port) {
			super(String.format("MasterListener-%s-[%s:%d]", masterName, host,
					port));
			this.masterName = masterName;
			this.host = host;
			this.port = port;
		}

		/**
		 * <p>Title: RedisMasterListener</p>
		 * <p>Description: 构造方法</p>
		 *
		 * @param masterName 主机名称
		 * @param host 地址
		 * @param port 端口
		 * @param subscribeRetryWaitTimeMillis 订阅重试等待时间
		 */
		public RedisMasterListener(String masterName, String host, int port,
				long subscribeRetryWaitTimeMillis) {
			this(masterName, host, port);
			this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}

		@Override
		public void run() {

			running.set(true);

			while (running.get()) {

				j = new Jedis(host, port);

				try {
					// double check that it is not being shutdown
					if (!running.get()) {
						break;
					}

					j.subscribe(new JedisPubSub() {
						
						@Override
						public void onMessage(String channel, String message) {
							log.fine("Sentinel " + host + ":" + port
									+ " published: " + message + ".");

							String[] switchMasterMsg = message.split(" ");

							if (switchMasterMsg.length > 3) {

								if (masterName.equals(switchMasterMsg[0])) {
									initPool(toHostAndPort(Arrays.asList(
											switchMasterMsg[3],
											switchMasterMsg[4])));
								} else {
									log.fine("Ignoring message on +switch-master for master name "
											+ switchMasterMsg[0]
											+ ", our master name is "
											+ masterName);
								}

							} else {
								log.severe("Invalid message received on Sentinel "
										+ host
										+ ":"
										+ port
										+ " on channel +switch-master: "
										+ message);
							}
						}
					}, "+switch-master");

				} catch (Exception e) {

					if (running.get()) {
						log.severe("Lost connection to Sentinel at " + host
								+ ":" + port
								+ ". Sleeping 5000ms and retrying.");
						try {
							Thread.sleep(subscribeRetryWaitTimeMillis);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					} else {
						log.fine("Unsubscribing from Sentinel at " + host + ":"
								+ port);
					}
				} finally {
					j.close();
				}
			}
		}

		/**
		 * <p>Title: shutdown</p>
		 * <p>Description: 关闭监听器</p>
		 */
		public void shutdown() {
			try {
				log.fine("Shutting down listener on " + host + ":" + port);
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				if (j != null) {
					j.disconnect();
				}
			} catch (Exception e) {
				log.log(Level.SEVERE, "Caught exception while shutting down: ",
						e);
			}
		}
	}

	@Override
	public Jedis getConnection() {

		return this.getResource();
	}

	@Override
	public void returnConnection(Jedis conn) {
		
		Client client = conn.getClient();
		
		if (client.isBroken())

			this.invalidateResource(conn);
		else 
			this.returnResource(conn);
	}

	@Override
	public void invalidateConnection(Jedis conn) {

		this.invalidateResource(conn);		
	}
}
