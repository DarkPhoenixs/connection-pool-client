package org.darkphoenixs.pool;


import org.junit.Assert;
import org.junit.Test;

public class PoolConfigTest {

	@Test
	public void test() throws Exception {
		
		PoolConfig config = new PoolConfig();
		
		Assert.assertNotNull(config);

		Assert.assertEquals(PoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS, 60000);
		
		Assert.assertEquals(PoolConfig.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS, 30000);

		Assert.assertEquals(PoolConfig.DEFAULT_TEST_WHILE_IDLE, true);
		
		Assert.assertEquals(PoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN, -1);
	}
}
