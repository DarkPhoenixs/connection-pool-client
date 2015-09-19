Connection Pool Client
==============================
  基于[Apache Commons Pool ™](http://commons.apache.org/proper/commons-pool/)框架

  [Apache Kafka](http://kafka.apache.org/) &amp; [Apache Hbase](http://hbase.apache.org/) &amp; [Redis](http://redis.io/) 连接池客户端
  
  * [Documentation](#documentation)
  * [KafkaConnectionPool](#kafkaconnectionpool)
  * [HbaseConnectionPool](#hbaseconnectionpool)
  * [RedisConnectionPool](#redisconnectionpool)


## Documentation

API documentation is available at [this]()

## KafkaConnectionPool

Use the [KafkaConnectionPool](https://github.com/darkphoenixs/connection-pool-client/blob/master/src/main/java/org/darkphoenixs/pool/kafka/KafkaConnectionPool.java) must instantiate `PoolConfig` and `Properties`

For example, the following 
```java
		/* poolConfig */
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);

		/* properties */
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", "localhost:9092");
		props.setProperty("producer.type", "async");
		props.setProperty("request.required.acks", "0");
		props.setProperty("compression.codec", "snappy");
		props.setProperty("batch.num.messages", "200");

		/* connection pool */
		KafkaConnectionPool pool = new KafkaConnectionPool(config, props);

		/* pool getConnection */
		Producer<byte[], byte[]> producer = pool.getConnection();

		/* message */
		KeyedMessage<byte[], byte[]> message = new KeyedMessage<>(...);

		/* producer send */
		producer.send(message);

		/* pool returnConnection */
		pool.returnConnection(producer);
```
## HbaseConnectionPool

## RedisConnectionPool
