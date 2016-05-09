package org.darkphoenixs.pool.kafka;

import org.junit.Test;

public class KafkaTest {
	
	@Test
	public void test() throws Exception {

//		PoolConfig config = new PoolConfig();
//		config.setMaxTotal(20);
//		config.setMaxIdle(5);
//		config.setMaxWaitMillis(1000);
//		config.setTestOnBorrow(true);
//
//		Properties props = new Properties();
//		props.setProperty("metadata.broker.list", "localhost:9092");
//		props.setProperty("producer.type", "async");
//		props.setProperty("request.required.acks", "0");
//		props.setProperty("compression.codec", "snappy");
//		props.setProperty("batch.num.messages", "200");
//
//		KafkaConnectionPool pool = new KafkaConnectionPool(config, props);
//
//		for (int i = 0; i < 1000; i++) {
//
//			Producer<byte[], byte[]> producer = pool.getConnection();
//
//			KeyedMessage<byte[], byte[]> message = new KeyedMessage<byte[], byte[]>(
//					"QUEUE.TEST", String.valueOf(i).getBytes(), String.valueOf(
//							"Test" + i).getBytes());
//
//			producer.send(message);
//
//			pool.returnConnection(producer);
//		}
//
//		pool.close();
	}
}
