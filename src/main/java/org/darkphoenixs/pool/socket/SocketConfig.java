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

/**
 * <p>SocketConfig</p>
 * <p>Socket配置</p>
 *
 * @since 1.2.1
 * @author Victor
 *
 */
public interface SocketConfig {

	/** DEFAULT_HOST */
	public static final String DEFAULT_HOST = "localhost";
	/** DEFAULT_PORT */
	public static final int DEFAULT_PORT = 1234;
	/** DEFAULT_TIMEOUT */
	public static final int DEFAULT_TIMEOUT = 2000;
	/** DEFAULT_BUFFERSIZE */
	public static final int DEFAULT_BUFFERSIZE = 3 * 1024;
	/** DEFAULT_LINGER */
	public static final int DEFAULT_LINGER = 0;
	/** DEFAULT_KEEPALIVE */
	public static final boolean DEFAULT_KEEPALIVE = false;
	/** DEFAULT_TCPNODELAY */
	public static final boolean DEFAULT_TCPNODELAY = false;
	/** DEFAULT_PERFORMANCE */
	public static final String[] DEFAULT_PERFORMANCE = null;
	
	/** ADDRESS_PROPERTY */
	public static final String ADDRESS_PROPERTY = "address";
	/** RECE_BUFFERSIZE_PROPERTY */
	public static final String RECE_BUFFERSIZE_PROPERTY = "receiveBufferSize";
	/** SEND_BUFFERSIZE_PROPERTY */
	public static final String SEND_BUFFERSIZE_PROPERTY = "sendBufferSize";
	/** CONN_TIMEOUT_PROPERTY */
	public static final String CONN_TIMEOUT_PROPERTY = "connectionTimeout";
	/** SO_TIMEOUT_PROPERTY */
	public static final String SO_TIMEOUT_PROPERTY = "soTimeout";
	/** LINGER_PROPERTY */
	public static final String LINGER_PROPERTY = "linger";
	/** KEEPALIVE_PROPERTY */
	public static final String KEEPALIVE_PROPERTY = "keepAlive";
	/** TCPNODELAY_PROPERTY */
	public static final String TCPNODELAY_PROPERTY = "tcpNoDelay";
	/** PERFORMANCE_PROPERTY */
	public static final String PERFORMANCE_PROPERTY = "performance";
}
