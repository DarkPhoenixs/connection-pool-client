package org.darkphoenixs.pool.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShardedJedisConn3 extends ShardedJedis {

    public ShardedJedisConn3(List<JedisShardInfo> shards) {
        super(shards);
    }

    @Override
    public Collection<Jedis> getAllShards() {

        Jedis jedis = new JedisConn3();

        return Collections.singletonList(jedis);
    }

}
