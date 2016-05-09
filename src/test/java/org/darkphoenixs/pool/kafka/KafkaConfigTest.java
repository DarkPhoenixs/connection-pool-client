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

import org.junit.Assert;
import org.junit.Test;

public class KafkaConfigTest {

	@Test
	public void test() throws Exception {

		Assert.assertEquals(KafkaConfig.DEFAULT_BROKERS, "localhost:9092");
		Assert.assertEquals(KafkaConfig.DEFAULT_TYPE, "sync");
		Assert.assertEquals(KafkaConfig.DEFAULT_ACKS, "0");
		Assert.assertEquals(KafkaConfig.DEFAULT_CODEC, "none");
		Assert.assertEquals(KafkaConfig.DEFAULT_BATCH, "200");
		
		Assert.assertEquals(KafkaConfig.BROKERS_LIST_PROPERTY, "metadata.broker.list");
		Assert.assertEquals(KafkaConfig.PRODUCER_TYPE_PROPERTY, "producer.type");
		Assert.assertEquals(KafkaConfig.REQUEST_ACKS_PROPERTY, "request.required.acks");
		Assert.assertEquals(KafkaConfig.COMPRESSION_CODEC_PROPERTY, "compression.codec");
		Assert.assertEquals(KafkaConfig.BATCH_NUMBER_PROPERTY, "batch.num.messages");
	}
}
