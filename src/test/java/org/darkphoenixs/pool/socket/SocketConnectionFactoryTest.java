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

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

public class SocketConnectionFactoryTest {

	@Test
	public void test() throws Exception {

		SocketConnectionFactory factory = new SocketConnectionFactory(
				SocketConfig.DEFAULT_HOST, SocketConfig.DEFAULT_PORT,
				SocketConfig.DEFAULT_BUFFERSIZE,
				SocketConfig.DEFAULT_BUFFERSIZE, SocketConfig.DEFAULT_TIMEOUT,
				SocketConfig.DEFAULT_TIMEOUT, 1,
				true,
				true,
				new String[]{"0","1","2"});
		
		try {
			factory.makeObject();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			factory.activateObject(new DefaultPooledObject<Socket>(new Socket()));
		} catch (Exception e) {
		}
		
		try {
			factory.validateObject(new DefaultPooledObject<Socket>(new Socket()));
		} catch (Exception e) {
		}
		
		try {
			factory.passivateObject(new DefaultPooledObject<Socket>(new Socket()));
		} catch (Exception e) {
		}
		
		try {
			factory.destroyObject(new DefaultPooledObject<Socket>(new Socket()));
		} catch (Exception e) {
		}

	}
}
