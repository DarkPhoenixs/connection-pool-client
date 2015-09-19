package org.darkphoenixs.pool;

import org.darkphoenixs.pool.redis.RedisConnectionPool;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

	@Test
	public void test() throws Exception {
		
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		
		RedisConnectionPool pool = new RedisConnectionPool(config, "localhost", 6379);
		
		for (int i = 0; i < 1000; i++) {
			
			Jedis jedis = pool.getConnection();
			
			jedis.set("key", "value");
			
			pool.returnConnection(jedis);
		}
		
		pool.close();
	}
}
