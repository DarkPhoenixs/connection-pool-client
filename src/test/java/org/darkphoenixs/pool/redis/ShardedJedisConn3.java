package org.darkphoenixs.pool.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class ShardedJedisConn3 extends ShardedJedis {

	public ShardedJedisConn3(List<JedisShardInfo> shards) {
		super(shards);
	}

	@Override
	public Collection<Jedis> getAllShards() {

		Jedis jedis = new JedisConn();
		
		return Collections.singletonList(jedis);
	}
	
}
