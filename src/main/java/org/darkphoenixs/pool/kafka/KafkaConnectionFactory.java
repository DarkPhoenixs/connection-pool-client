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

import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionException;
import org.darkphoenixs.pool.ConnectionFactory;

import java.util.Properties;

/**
 * <p>Title: KafkaConnectionFactory</p>
 * <p>Description: Kafka连接工厂</p>
 *
 * @author Victor
 * @version 1.0
 * @see ConnectionFactory
 * @since 2015年9月19日
 */
class KafkaConnectionFactory implements ConnectionFactory<Producer<byte[], byte[]>> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8271607366818512399L;

    /**
     * config
     */
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
     * @param type    生产者类型
     * @param acks    确认类型
     * @param codec   压缩类型
     * @param batch   批量大小
     */
    public KafkaConnectionFactory(final String brokers, final String type, final String acks, final String codec, final String batch) {

        Properties props = new Properties();
        props.setProperty(KafkaConfig.BROKERS_LIST_PROPERTY, brokers);
        props.setProperty(KafkaConfig.PRODUCER_TYPE_PROPERTY, type);
        props.setProperty(KafkaConfig.REQUEST_ACKS_PROPERTY, acks);
        props.setProperty(KafkaConfig.COMPRESSION_CODEC_PROPERTY, codec);
        props.setProperty(KafkaConfig.BATCH_NUMBER_PROPERTY, batch);
        this.config = new ProducerConfig(props);
    }

    /**
     * @param properties 参数配置
     * @since 1.2.1
     */
    public KafkaConnectionFactory(final Properties properties) {

        String brokers = properties.getProperty(KafkaConfig.BROKERS_LIST_PROPERTY);
        if (brokers == null)
            throw new ConnectionException("[" + KafkaConfig.BROKERS_LIST_PROPERTY + "] is required !");

        this.config = new ProducerConfig(properties);
    }

    @Override
    public PooledObject<Producer<byte[], byte[]>> makeObject() throws Exception {

        Producer<byte[], byte[]> producer = this.createConnection();

        return new DefaultPooledObject<Producer<byte[], byte[]>>(producer);
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

        Producer<byte[], byte[]> producer = new Producer<byte[], byte[]>(config);

        return producer;
    }
}
