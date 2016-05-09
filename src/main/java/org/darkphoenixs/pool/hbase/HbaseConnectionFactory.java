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

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.darkphoenixs.pool.ConnectionException;
import org.darkphoenixs.pool.ConnectionFactory;

/**
 * <p>Title: HbaseConnectionFactory</p>
 * <p>Description: Hbase连接工厂</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see ConnectionFactory
 * @version 1.0
 */
class HbaseConnectionFactory implements ConnectionFactory<Connection> {

	/** serialVersionUID */
	private static final long serialVersionUID = 4024923894283696465L;

	/** hadoopConfiguration */
	private final Configuration hadoopConfiguration;

	/**
	 * <p>Title: HbaseConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param hadoopConfiguration hbase配置
	 */
	public HbaseConnectionFactory(final Configuration hadoopConfiguration) {

		this.hadoopConfiguration = hadoopConfiguration;
	}
	
	/**
	 * <p>Title: HbaseConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param host zookeeper地址
	 * @param port zookeeper端口
	 * @param master hbase主机
	 * @param rootdir hdfs数据目录
	 */
	public HbaseConnectionFactory(final String host, final String port, final String master, final String rootdir) {

		this.hadoopConfiguration = new Configuration();
		
		if (host == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY, host);
		
		if (port == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY, port);
		
		if (master != null)
			this.hadoopConfiguration.set(HbaseConfig.MASTER_PROPERTY, master);
		
		if (rootdir != null)
			this.hadoopConfiguration.set(HbaseConfig.ROOTDIR_PROPERTY, rootdir);
	}

	/**
	 * @since 1.2.1
	 * @param properties 参数配置
	 */
	public HbaseConnectionFactory(final Properties properties) {
		
		this.hadoopConfiguration = new Configuration();
		
		String host = properties.getProperty(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY);
		if (host == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY, host);

		String port = properties.getProperty(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY);
		if (port == null)
			throw new ConnectionException("[" + HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY + "] is required !");
		this.hadoopConfiguration.set(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY, port);

		String master = properties.getProperty(HbaseConfig.MASTER_PROPERTY);
		if (master != null)
			this.hadoopConfiguration.set(HbaseConfig.MASTER_PROPERTY, master);

		String rootdir = properties.getProperty(HbaseConfig.ROOTDIR_PROPERTY);
		if (rootdir != null)
			this.hadoopConfiguration.set(HbaseConfig.ROOTDIR_PROPERTY, rootdir);
	}
	
	@Override
	public PooledObject<Connection> makeObject() throws Exception {
		
		Connection connection = this.createConnection();
		
		return new DefaultPooledObject<Connection>(connection);	
	}

	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {

		Connection connection = p.getObject();

		if (connection != null)

			connection.close();
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p) {

		Connection connection = p.getObject();
		
		if (connection != null) 
			
			return ((!connection.isAborted()) && (!connection.isClosed()));
		
		return false;
	}

	@Override
	public void activateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void passivateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Connection createConnection() throws Exception {

		Connection connection = org.apache.hadoop.hbase.client.ConnectionFactory
				.createConnection(hadoopConfiguration);
		
		return connection;
	}

}
