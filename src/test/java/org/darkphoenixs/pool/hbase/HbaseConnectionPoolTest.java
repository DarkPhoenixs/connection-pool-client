/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.darkphoenixs.pool.hbase;

import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.darkphoenixs.pool.PoolConfig;
import org.junit.Test;

public class HbaseConnectionPoolTest {

	@Test
	public void test() throws Exception {

		try {
			new HbaseConnectionPool(new PoolConfig(), null, null);

		} catch (Exception e) {
		}

		try {
			new HbaseConnectionPool(null, null, null, null);

		} catch (Exception e) {
		}

		try {
			new HbaseConnectionPool(new Configuration());

		} catch (Exception e) {
		}

		try {
			new HbaseConnectionPool(new PoolConfig(), new Properties()).close();
		} catch (Exception e) {
		}

		HbaseConnectionPool pool = new HbaseConnectionPool();

		try {
			pool.getConnection();
		} catch (Exception e) {
		}

		try {
			pool.returnConnection(null);
		} catch (Exception e) {
		}

		try {
			pool.invalidateConnection(null);
		} catch (Exception e) {
		}

		pool.close();
	}
}
