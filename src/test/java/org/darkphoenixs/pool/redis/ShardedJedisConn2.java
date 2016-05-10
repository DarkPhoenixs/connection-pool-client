package org.darkphoenixs.pool.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.darkphoenixs.pool.ConnectionException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class ShardedJedisConn2 extends ShardedJedis {

	public ShardedJedisConn2(List<JedisShardInfo> shards) {
		super(shards);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Collection<Jedis> getAllShards() {

		Jedis jedis = new Jedis() {
			
			@Override
			public String ping() {

				throw new ConnectionException("ping");
			}
		};
		return Collections.singletonList(jedis);
	}
	
}
