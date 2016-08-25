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

import kafka.producer.ProducerConfig;
import org.darkphoenixs.pool.PoolConfig;
import org.junit.Test;

import java.util.Properties;

public class KafkaConnectionPoolTest {

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
            KafkaConnectionPool pool = new KafkaConnectionPool(KafkaConfig.DEFAULT_BROKERS);
            pool.close();
        } catch (Exception e) {
        }

        try {
            KafkaConnectionPool pool = new KafkaConnectionPool(prop);
            pool.close();
        } catch (Exception e) {
        }

        try {
            KafkaConnectionPool pool = new KafkaConnectionPool(config);
            pool.close();
        } catch (Exception e) {
        }

        try {
            KafkaConnectionPool pool = new KafkaConnectionPool(new PoolConfig(), prop);
            pool.close();
        } catch (Exception e) {
        }

        try {
            KafkaConnectionPool pool = new KafkaConnectionPool(new PoolConfig(), config);
            pool.close();
        } catch (Exception e) {
        }

        try {
            KafkaConnectionPool pool = new KafkaConnectionPool(new PoolConfig(), KafkaConfig.DEFAULT_BROKERS, KafkaConfig.DEFAULT_TYPE);
            pool.close();
        } catch (Exception e) {
        }

        KafkaConnectionPool pool = new KafkaConnectionPool();

        try {
            pool.getConnection();
        } catch (Exception e) {
        }

        try {
            pool.returnConnection(null);
        } catch (Exception e) {
        }

        try {
            pool.invalidateConnection(null);
        } catch (Exception e) {
        }

        pool.close();
    }
}
