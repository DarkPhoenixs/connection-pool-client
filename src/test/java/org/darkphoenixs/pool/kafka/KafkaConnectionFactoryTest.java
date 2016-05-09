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
package org.darkphoenixs.pool.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

public class KafkaConnectionFactoryTest {

	@Test
	public void test() throws Exception {

		Properties prop = new Properties();

		prop.setProperty(KafkaConfig.BROKERS_LIST_PROPERTY,
				KafkaConfig.DEFAULT_BROKERS);
		prop.setProperty(KafkaConfig.PRODUCER_TYPE_PROPERTY,
				KafkaConfig.DEFAULT_TYPE);
		prop.setProperty(KafkaConfig.REQUEST_ACKS_PROPERTY,
				KafkaConfig.DEFAULT_ACKS);
		prop.setProperty(KafkaConfig.COMPRESSION_CODEC_PROPERTY,
				KafkaConfig.DEFAULT_CODEC);
		prop.setProperty(KafkaConfig.BATCH_NUMBER_PROPERTY,
				KafkaConfig.DEFAULT_BATCH);

		ProducerConfig config = new ProducerConfig(prop);

		try {
			KafkaConnectionFactory factory = new KafkaConnectionFactory(prop);
			factory.toString();
		} catch (Exception e) {
		}
		
		try {
			KafkaConnectionFactory factory = new KafkaConnectionFactory(config);
			factory.toString();
		} catch (Exception e) {
		}
		
		KafkaConnectionFactory factory = new KafkaConnectionFactory(
				KafkaConfig.DEFAULT_BROKERS, KafkaConfig.DEFAULT_TYPE,
				KafkaConfig.DEFAULT_ACKS, KafkaConfig.DEFAULT_CODEC,
				KafkaConfig.DEFAULT_BATCH);

		try {
			factory.makeObject();

		} catch (Exception e) {
		}

		factory.activateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
				new Producer<byte[], byte[]>(config)));
		try {
			factory.activateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
					null));
		} catch (Exception e) {
		}

		factory.validateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
				new Producer<byte[], byte[]>(config)));
		try {
			factory.validateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
					null));
		} catch (Exception e) {
		}

		factory.passivateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
				new Producer<byte[], byte[]>(config)));
		try {
			factory.passivateObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
					null));
		} catch (Exception e) {
		}

		factory.destroyObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
				new Producer<byte[], byte[]>(config)));
		try {
			factory.destroyObject(new DefaultPooledObject<Producer<byte[], byte[]>>(
					null));
		} catch (Exception e) {
		}

	}
}
