package org.darkphoenixs.pool.redis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class ShardedJedisConn5 extends ShardedJedis {

	public ShardedJedisConn5(List<JedisShardInfo> shards) {
		super(shards);
	}

	@Override
	public Collection<Jedis> getAllShards() {

		Jedis jedis = new Jedis() {
			
			@Override
			public Client getClient() {

				return new Client() {
					
					@Override
					public boolean isBroken() {

						return true;
					}
				};
			}
		};

		return Collections.singletonList(jedis);
	}

}
