package org.darkphoenixs.pool.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShardedJedisConn2 extends ShardedJedis {

    public ShardedJedisConn2(List<JedisShardInfo> shards) {
        super(shards);
    }

    @Override
    public Collection<Jedis> getAllShards() {

        Jedis jedis = new JedisConn2();

        return Collections.singletonList(jedis);
    }

}
