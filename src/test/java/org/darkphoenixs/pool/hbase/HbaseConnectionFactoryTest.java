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

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.hbase.client.Connection;
import org.junit.Test;

public class HbaseConnectionFactoryTest {

	@Test
	public void test() throws Exception {

		HbaseConnectionFactory factory = new HbaseConnectionFactory(
				HbaseConfig.DEFAULT_HOST, HbaseConfig.DEFAULT_PORT,
				"localhost:60000", "hdfs://localhost:8020/hbase");

		try {
			factory.makeObject();
		} catch (Exception e) {
		}

		try {
			factory.activateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.validateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.passivateObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}

		try {
			factory.destroyObject(new DefaultPooledObject<Connection>(null));
		} catch (Exception e) {
		}
		
	}
}
