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

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.BufferedMutatorParams;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.RegionLocator;
import org.apache.hadoop.hbase.client.Table;
import org.junit.Test;

public class HbaseConnectionFactoryTest {

	@Test
	public void test_0() throws Exception {

		try {
			new HbaseConnectionFactory(null, null, null, null);

		} catch (Exception e) {
		}

		try {
			new HbaseConnectionFactory(new Configuration());

		} catch (Exception e) {
		}
		
		try {
			new HbaseConnectionFactory(new Properties());

		} catch (Exception e) {
		}
		
		try {
			Properties prop = new Properties();
			
			prop.setProperty(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY, HbaseConfig.DEFAULT_HOST);
			prop.setProperty(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY, HbaseConfig.DEFAULT_PORT);
			prop.setProperty(HbaseConfig.MASTER_PROPERTY, "localhost:60000");
			prop.setProperty(HbaseConfig.ROOTDIR_PROPERTY, "hdfs://localhost:8020/hbase");
			
			new HbaseConnectionFactory(new Properties());

		} catch (Exception e) {
		}


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

		try {
			factory.destroyObject(new DefaultPooledObject<Connection>(
					new Connection() {

						@Override
						public void abort(String why, Throwable e) {

						}

						@Override
						public boolean isAborted() {
							return false;
						}

						@Override
						public Configuration getConfiguration() {
							return null;
						}

						@Override
						public Table getTable(TableName tableName)
								throws IOException {
							return null;
						}

						@Override
						public Table getTable(TableName tableName,
								ExecutorService pool) throws IOException {
							return null;
						}

						@Override
						public BufferedMutator getBufferedMutator(
								TableName tableName) throws IOException {
							return null;
						}

						@Override
						public BufferedMutator getBufferedMutator(
								BufferedMutatorParams params)
								throws IOException {
							return null;
						}

						@Override
						public RegionLocator getRegionLocator(
								TableName tableName) throws IOException {
							return null;
						}

						@Override
						public Admin getAdmin() throws IOException {
							return null;
						}

						@Override
						public void close() throws IOException {

						}

						@Override
						public boolean isClosed() {
							return false;
						}

					}));
		} catch (Exception e) {
		}
	}

}
