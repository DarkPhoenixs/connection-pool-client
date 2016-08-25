package org.darkphoenixs.pool.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.darkphoenixs.pool.ConnectionPool;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Title: KafkaSharedConnPool</p>
 * <p>Description: Kafka共享连接池（单例）</p>
 *
 * @author Victor.Zxy
 * @version 1.2.3
 * @see ConnectionPool
 * @since 2016 /8/23
 */
public class KafkaSharedConnPool implements ConnectionPool<Producer<byte[], byte[]>> {

    private static final AtomicReference<KafkaSharedConnPool> pool = new AtomicReference<KafkaSharedConnPool>();

    private final Producer<byte[], byte[]> producer;

    private KafkaSharedConnPool(Properties properties) {

        this.producer = new KafkaProducer<byte[], byte[]>(properties);
    }

    /**
     * Gets instance.
     *
     * @param brokers the brokers
     * @param codec   the codec
     * @param keySer  the key ser
     * @param valSer  the val ser
     * @return the instance
     */
    public synchronized static KafkaSharedConnPool getInstance(final String brokers, final String codec, final String keySer, final String valSer) {

        Properties properties = new Properties();

        properties.setProperty(KafkaConfig.BOOTSTRAP_SERVERS_PROPERTY, brokers);
        properties.setProperty(KafkaConfig.COMPRESSION_CODEC_PROPERTY, codec);
        properties.setProperty(KafkaConfig.KEY_SERIALIZER_PROPERTY, keySer);
        properties.setProperty(KafkaConfig.VAL_SERIALIZER_PROPERTY, valSer);

        return getInstance(properties);
    }

    /**
     * Gets instance.
     *
     * @param properties the properties
     * @return the instance
     */
    public synchronized static KafkaSharedConnPool getInstance(final Properties properties) {

        if (pool.get() == null)

            pool.set(new KafkaSharedConnPool(properties));

        return pool.get();
    }

    @Override
    public Producer<byte[], byte[]> getConnection() {

        return producer;
    }

    @Override
    public void returnConnection(Producer<byte[], byte[]> conn) {

        if (conn != null)

            conn.flush();
    }

    @Override
    public void invalidateConnection(Producer<byte[], byte[]> conn) {

        if (conn != null)

            conn.close();
    }

    /**
     * Close.
     */
    public void close() {

        producer.flush();
        
        producer.close();

        pool.set(null);
    }
}
