package org.darkphoenixs.pool.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class ShardedJedisConn4 extends ShardedJedis {

	public ShardedJedisConn4(List<JedisShardInfo> shards) {
		super(shards);
	}

	@Override
	public Collection<Jedis> getAllShards() {

		Jedis jedis = new JedisConn4();
		
		return Collections.singletonList(jedis);
	}
	
}
