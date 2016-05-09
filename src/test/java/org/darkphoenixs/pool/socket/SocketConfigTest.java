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


import org.darkphoenixs.pool.socket.SocketConfig;
import org.junit.Assert;
import org.junit.Test;

public class SocketConfigTest {

	@Test
	public void test() throws Exception {
		
		Assert.assertEquals(SocketConfig.DEFAULT_HOST, "localhost");
		Assert.assertEquals(SocketConfig.DEFAULT_PORT, 1234 );
		Assert.assertEquals(SocketConfig.DEFAULT_TIMEOUT, 2000 );
		Assert.assertEquals(SocketConfig.DEFAULT_BUFFERSIZE, 3072);
		Assert.assertEquals(SocketConfig.DEFAULT_LINGER, 0);
		Assert.assertFalse(SocketConfig.DEFAULT_KEEPALIVE);
		Assert.assertFalse(SocketConfig.DEFAULT_TCPNODELAY);
		Assert.assertNull(SocketConfig.DEFAULT_PERFORMANCE);
		
		Assert.assertEquals(SocketConfig.ADDRESS_PROPERTY, "address");
		Assert.assertEquals(SocketConfig.RECE_BUFFERSIZE_PROPERTY, "receiveBufferSize");
		Assert.assertEquals(SocketConfig.SEND_BUFFERSIZE_PROPERTY, "sendBufferSize");
		Assert.assertEquals(SocketConfig.CONN_TIMEOUT_PROPERTY, "connectionTimeout");
		Assert.assertEquals(SocketConfig.SO_TIMEOUT_PROPERTY, "soTimeout");
		Assert.assertEquals(SocketConfig.LINGER_PROPERTY, "linger");
		Assert.assertEquals(SocketConfig.KEEPALIVE_PROPERTY, "keepAlive");
		Assert.assertEquals(SocketConfig.TCPNODELAY_PROPERTY, "tcpNoDelay");
		Assert.assertEquals(SocketConfig.PERFORMANCE_PROPERTY, "performance");
	}
	
}
