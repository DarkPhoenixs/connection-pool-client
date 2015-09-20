/**
 * <p>Title: RedisShardedConnPool.java</p>
 * <p>Description: RedisShardedConnPool</p>
 * <p>Package: org.darkphoenixs.pool.redis</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool.redis;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionFactory;
import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.util.Hashing;

/**
 * <p>Title: RedisShardedConnPool</p>
 * <p>Description: Redis分片连接池</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see PoolBase
 * @see ConnectionPool
 * @version 1.0
 */
public class RedisShardedConnPool extends PoolBase<ShardedJedis> implements
		ConnectionPool<ShardedJedis> {

	/** serialVersionUID */
	private static final long serialVersionUID = -273019562990831722L;

	/**
	 * <p>Title: RedisShardedConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param shards 分片列表
	 */
	public RedisShardedConnPool(final PoolConfig poolConfig,
			List<JedisShardInfo> shards) {
		this(poolConfig, shards, Hashing.MURMUR_HASH);
	}

	/**
	 * <p>Title: RedisShardedConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param shards 分片列表
	 * @param algo 数字哈希
	 */
	public RedisShardedConnPool(final PoolConfig poolConfig,
			List<JedisShardInfo> shards, Hashing algo) {
		this(poolConfig, shards, algo, null);
	}

	/**
	 * <p>Title: RedisShardedConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param shards 分片列表
	 * @param keyTagPattern 键表达式
	 */
	public RedisShardedConnPool(final PoolConfig poolConfig,
			List<JedisShardInfo> shards, Pattern keyTagPattern) {
		this(poolConfig, shards, Hashing.MURMUR_HASH, keyTagPattern);
	}

	/**
	 * <p>Title: RedisShardedConnPool</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param poolConfig 池配置
	 * @param shards 分片列表
	 * @param algo 数字哈希
	 * @param keyTagPattern 键表达式
	 */
	public RedisShardedConnPool(final PoolConfig poolConfig,
			List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
		super(poolConfig, new RedisShardedConnFactory(shards, algo,
				keyTagPattern));
	}

	/**
	 * <p>Title: RedisShardedConnFactory</p>
	 * <p>Description: Redis分片连接工厂</p>
	 *
	 * @since 2015年9月19日
	 * @author Victor
	 * @see ConnectionFactory
	 * @version 1.0
	 */
	protected static class RedisShardedConnFactory implements
			ConnectionFactory<ShardedJedis> {

		/** serialVersionUID */
		private static final long serialVersionUID = -8620587542840002906L;
		/** shards */
		private List<JedisShardInfo> shards;
		/** algo */
		private Hashing algo;
		/** keyTagPattern */
		private Pattern keyTagPattern;

		/**
		 * <p>Title: RedisShardedConnFactory</p>
		 * <p>Description: 构造方法</p>
		 *
		 * @param shards 分片列表
		 * @param algo 数字哈希
		 * @param keyTagPattern 键表达式
		 */
		public RedisShardedConnFactory(List<JedisShardInfo> shards,
				Hashing algo, Pattern keyTagPattern) {
			this.shards = shards;
			this.algo = algo;
			this.keyTagPattern = keyTagPattern;
		}

		@Override
		public PooledObject<ShardedJedis> makeObject() throws Exception {
			ShardedJedis jedis = this.createConnection();
			return new DefaultPooledObject<ShardedJedis>(jedis);
		}

		@Override
		public void destroyObject(PooledObject<ShardedJedis> pooledShardedJedis)
				throws Exception {
			final ShardedJedis shardedJedis = pooledShardedJedis.getObject();
			for (Jedis jedis : shardedJedis.getAllShards()) {
				try {
					try {
						jedis.quit();
					} catch (Exception e) {

					}
					jedis.disconnect();
				} catch (Exception e) {

				}
			}
		}

		@Override
		public boolean validateObject(
				PooledObject<ShardedJedis> pooledShardedJedis) {
			try {
				ShardedJedis jedis = pooledShardedJedis.getObject();
				for (Jedis shard : jedis.getAllShards()) {
					if (!shard.ping().equals("PONG")) {
						return false;
					}
				}
				return true;
			} catch (Exception ex) {
				return false;
			}
		}

		@Override
		public void activateObject(PooledObject<ShardedJedis> p)
				throws Exception {

		}

		@Override
		public void passivateObject(PooledObject<ShardedJedis> p)
				throws Exception {

		}

		@Override
		public ShardedJedis createConnection() throws Exception {
			ShardedJedis jedis = new ShardedJedis(shards, algo, keyTagPattern);
			return jedis;
		}
	}

	@Override
	public ShardedJedis getConnection() {

		return super.getResource();
	}

	@Override
	public void returnConnection(ShardedJedis conn) {

		boolean broken = false;

		for (Jedis jedis : conn.getAllShards()) 
			if (jedis.getClient().isBroken()) {
				broken = true;
				break;
			}

		if (broken)
			super.invalidateResource(conn);
		else 
			super.returnResource(conn);
	}

	@Override
	public void invalidateConnection(ShardedJedis conn) {

		super.invalidateResource(conn);
	}

}
