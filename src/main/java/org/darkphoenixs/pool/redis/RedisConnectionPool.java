/**
 * <p>Title: RedisConnectionPool.java</p>
 * <p>Description: RedisConnectionPool</p>
 * <p>Package: org.darkphoenixs.pool.redis</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool.redis;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

/**
 * <p>Title: RedisConnectionPool</p>
 * <p>Description: Redis连接池</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see ConnectionPool
 * @see PoolBase
 * @version 1.0
 */
public class RedisConnectionPool extends PoolBase<Jedis> implements ConnectionPool<Jedis> {

	/** serialVersionUID */
	private static final long serialVersionUID = -7068775808649440797L;

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 默认构造方法</p>
	 *
	 */
	public RedisConnectionPool() {
		this("localhost", 6379);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host) {
		this(poolConfig, host, 6379, 2000, null, 0, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param host 地址
	 * @param port 端口
	 */
	public RedisConnectionPool(final String host, final int port) {
		this(new PoolConfig(), host, port, 2000, null, 0, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param timeout 超时
	 * @param password 密码
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
			final int timeout, final String password) {
		this(poolConfig, host, port, timeout, password, 0, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port) {
		this(poolConfig, host, port, 2000, null, 0, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param timeout 超时
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
			final int timeout) {
		this(poolConfig, host, port, timeout, null, 0, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param timeout 超时
	 * @param password 密码
	 * @param database 数据库
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
			final int timeout, final String password, final int database) {
		this(poolConfig, host, port, timeout, password, database, null);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param timeout 超时
	 * @param password 密码
	 * @param database 数据库
	 * @param clientName 客户端名称
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
			final int timeout, final String password, final int database, final String clientName) {
		this(poolConfig, host, port, timeout, timeout, password, database,
				clientName);
	}

	/**
	 * <p>Title: RedisConnectionPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param connectionTimeout 连接超时
	 * @param soTimeout 超时时间
	 * @param password 密码
	 * @param database 数据库
	 * @param clientName 客户端名称
	 */
	public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
			final int connectionTimeout, final int soTimeout, final String password,
			final int database, final String clientName) {
		super(poolConfig, new RedisConnectionFactory(host, port, connectionTimeout,
				soTimeout, password, database, clientName));
	}

	@Override
	public Jedis getConnection() {

		return super.getResource();
	}

	@Override
	public void returnConnection(Jedis conn) {
		
		Client client = conn.getClient();
		
		if (client.isBroken())

			super.invalidateResource(conn);
		else 
			super.returnResource(conn);
	}
	
	@Override
	public void invalidateConnection(Jedis conn) {

		super.invalidateResource(conn);		
	}
}
