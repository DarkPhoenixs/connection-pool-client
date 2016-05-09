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
package org.darkphoenixs.pool.socket;

import java.net.Socket;
import java.util.Properties;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

/**
 * <p>SocketConnectionPool</p>
 * <p>Socket连接池</p>
 * 
 * @author Victor.Zxy
 * @see PoolBase
 * @see ConnectionPool
 * @since 1.2.1
 */
public class SocketConnectionPool extends PoolBase<Socket> implements ConnectionPool<Socket> {

	/** serialVersionUID */
	private static final long serialVersionUID = 4231761143726425981L;

	/**
	 * 默认构造方法
	 */
	public SocketConnectionPool() {

		this(new PoolConfig(), SocketConfig.DEFAULT_HOST, SocketConfig.DEFAULT_PORT);
	}
	
	/**
	 * @param properties 参数配置
	 */
	public SocketConnectionPool(final Properties properties) {
		
		this(new PoolConfig(), properties);
	}
	
	/**
	 * @param poolConfig 池配置
	 * @param properties 参数配置
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final Properties properties) {
		
		super(poolConfig, new SocketConnectionFactory(properties));
	}
	
	/**
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final String host, final int port) {
		
		this(poolConfig, host, port, SocketConfig.DEFAULT_BUFFERSIZE, SocketConfig.DEFAULT_TIMEOUT);
	}

	/**
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param bufferSize 缓存大小
	 * @param timeout 超时时间
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final String host, final int port, final int bufferSize, final int timeout) {
		
		this(poolConfig, host, port, bufferSize, timeout, SocketConfig.DEFAULT_LINGER);
	}
	
	/**
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param bufferSize 缓存大小
	 * @param timeout 超时时间
	 * @param linger 逗留时间
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final String host, final int port, final int bufferSize, final int timeout, final int linger) {
		
		this(poolConfig, host, port, bufferSize, timeout, linger, SocketConfig.DEFAULT_KEEPALIVE, SocketConfig.DEFAULT_TCPNODELAY);
	}

	/**
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param bufferSize 缓存大小
	 * @param timeout 超时时间
	 * @param linger 逗留时间
	 * @param keepAlive 保持活动
	 * @param tcpNoDelay 不延迟
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final String host, final int port, final int bufferSize, final int timeout, final int linger, final boolean keepAlive, final boolean tcpNoDelay) {
		
		this(poolConfig, host, port, bufferSize, timeout, linger, keepAlive, tcpNoDelay, SocketConfig.DEFAULT_PERFORMANCE);
	}
	
	/**
	 * @param poolConfig 池配置
	 * @param host 地址
	 * @param port 端口
	 * @param bufferSize 缓存大小
	 * @param timeout 超时时间
	 * @param linger 逗留时间
	 * @param keepAlive 保持活动
	 * @param tcpNoDelay 不延迟
	 * @param performance 性能属性
	 */
	public SocketConnectionPool(final PoolConfig poolConfig, final String host, final int port, final int bufferSize, final int timeout, final int linger, final boolean keepAlive, final boolean tcpNoDelay, final String[] performance) {
		
		super(poolConfig, new SocketConnectionFactory(host, port, bufferSize, bufferSize, timeout, timeout, linger, keepAlive, tcpNoDelay, performance));
	}
	
	@Override
	public Socket getConnection() {

		return super.getResource();
	}

	@Override
	public void returnConnection(Socket conn) {

		super.returnResource(conn);
	}

	@Override
	public void invalidateConnection(Socket conn) {
		
		super.invalidateResource(conn);
	}

}
