package org.darkphoenixs.pool.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.junit.Test;

public class KafkaSharedConnPoolTest {

    @Test
    public void test() throws Exception {

        KafkaSharedConnPool pool = KafkaSharedConnPool.getInstance(KafkaConfig.DEFAULT_BROKERS,
                KafkaConfig.DEFAULT_CODEC, KafkaConfig.DEFAULT_KEY_SERIALIZER, KafkaConfig.DEFAULT_VAL_SERIALIZER);

        pool = KafkaSharedConnPool.getInstance(KafkaConfig.DEFAULT_BROKERS,
                KafkaConfig.DEFAULT_CODEC, KafkaConfig.DEFAULT_KEY_SERIALIZER, KafkaConfig.DEFAULT_VAL_SERIALIZER);

        Producer<byte[], byte[]> producer = pool.getConnection();

        pool.returnConnection(producer);

        pool.returnConnection(null);

        pool.invalidateConnection(producer);

        pool.invalidateConnection(null);

        pool.close();
    }
}