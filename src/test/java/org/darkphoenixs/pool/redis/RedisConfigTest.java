package org.darkphoenixs.pool.redis;

import org.junit.Assert;
import org.junit.Test;

public class RedisConfigTest {

	@Test
	public void test() throws Exception {
		
		Assert.assertEquals(RedisConfig.DEFAULT_HOST, "localhost");
		Assert.assertEquals(RedisConfig.DEFAULT_PORT, 6379);
		Assert.assertEquals(RedisConfig.DEFAULT_TIMEOUT, 2000);
		Assert.assertEquals(RedisConfig.DEFAULT_DATABASE, 0);
		Assert.assertNull(RedisConfig.DEFAULT_PASSWORD);
		Assert.assertNull(RedisConfig.DEFAULT_CLIENTNAME);
		
		Assert.assertEquals(RedisConfig.ADDRESS_PROPERTY, "address");
		Assert.assertEquals(RedisConfig.CONN_TIMEOUT_PROPERTY, "connectionTimeout");
		Assert.assertEquals(RedisConfig.SO_TIMEOUT_PROPERTY, "soTimeout");
		Assert.assertEquals(RedisConfig.DATABASE_PROPERTY, "database");
		Assert.assertEquals(RedisConfig.PASSWORD_PROPERTY, "password");
		Assert.assertEquals(RedisConfig.CLIENTNAME_PROPERTY, "clientName");
		Assert.assertEquals(RedisConfig.MASTERNAME_PROPERTY, "masterName");
		Assert.assertEquals(RedisConfig.SENTINELS_PROPERTY, "sentinels");

	}
}
