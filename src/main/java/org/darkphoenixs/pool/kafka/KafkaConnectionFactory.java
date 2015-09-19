/**
 * <p>Title: KafkaConnectionFactory.java</p>
 * <p>Description: KafkaConnectionFactory</p>
 * <p>Package: org.darkphoenixs.pool.kafka</p>
 * <p>Company: www.github.com/DarkPhoenixs</p>
 * <p>Copyright: Dark Phoenixs (Open-Source Organization) 2015</p>
 */
package org.darkphoenixs.pool.kafka;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionFactory;

/**
 * <p>Title: KafkaConnectionFactory</p>
 * <p>Description: Kafka连接工厂</p>
 *
 * @since 2015年9月19日
 * @author Victor
 * @see ConnectionFactory
 * @version 1.0
 */
class KafkaConnectionFactory implements ConnectionFactory<Producer<byte[], byte[]>> {

	/** serialVersionUID */
	private static final long serialVersionUID = 8271607366818512399L;

	/** config */
	private final ProducerConfig config;
	
	/**
	 * <p>Title: KafkaConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param config 生产者配置
	 */
	public KafkaConnectionFactory(final ProducerConfig config) {

		this.config = config;
	}
	
	/**
	 * <p>Title: KafkaConnectionFactory</p>
	 * <p>Description: 构造方法</p>
	 *
	 * @param brokers broker列表
	 * @param type 生产者类型
	 * @param acks 确认类型
	 * @param codec 压缩类型
	 * @param batch 批量大小
	 */
	public KafkaConnectionFactory(final String brokers, final String type, final String acks, final String codec, final String batch) {
		
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", brokers);
		props.setProperty("producer.type", type);
		props.setProperty("request.required.acks", acks);
		props.setProperty("compression.codec", codec);
		props.setProperty("batch.num.messages", batch);
		this.config = new ProducerConfig(props);
	}
	
	@Override
	public PooledObject<Producer<byte[], byte[]>> makeObject() throws Exception {

		Producer<byte[], byte[]> producer = this.createConnection();
		
		return new DefaultPooledObject<>(producer);
	}

	@Override
	public void destroyObject(PooledObject<Producer<byte[], byte[]>> p)
			throws Exception {

		Producer<byte[], byte[]> producer = p.getObject();
		
		if (null != producer)
			
			producer.close();
	}

	@Override
	public boolean validateObject(PooledObject<Producer<byte[], byte[]>> p) {

		Producer<byte[], byte[]> producer = p.getObject();
		
		return (null != producer);
	}

	@Override
	public void activateObject(PooledObject<Producer<byte[], byte[]>> p)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(PooledObject<Producer<byte[], byte[]>> p)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producer<byte[], byte[]> createConnection() throws Exception {

		Producer<byte[], byte[]> producer = new Producer<>(config);
		
		return producer;
	}
}
